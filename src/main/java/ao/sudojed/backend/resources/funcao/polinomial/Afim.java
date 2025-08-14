package ao.sudojed.backend.resources.funcao.polinomial;

import ao.sudojed.backend.resources.funcao.Funcao;
import ao.sudojed.backend.resources.funcao.Intervalo;
import ao.sudojed.backend.resources.funcao.enums.ParidadeEnum;
import ao.sudojed.backend.resources.funcao.enums.Tendencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coeficienteLinear;
    private List<Double> raizes;
    private int grau;
    private String monotonia;
    private Map<String, Object> coeficientes;
    private Map<String, Object> estudoCompleto;

    public Afim(Double coeficienteAngular, Double coeficienteLinear) {
        this.coeficienteAngular = coeficienteAngular;
        this.coeficienteLinear = coeficienteLinear;
        this.grau = 1;
        this.raizes = new ArrayList<>();
        this.estudoCompleto = new HashMap<>();
        this.coeficientes = new HashMap<>();

        inicializarPropriedades();
    }

    public Afim(String expressao) {
        this.grau = 1;
        this.raizes = new ArrayList<>();
        this.estudoCompleto = new HashMap<>();
        this.coeficientes = new HashMap<>();

        parseExpressao(expressao);
        inicializarPropriedades();
    }

    private void parseExpressao(String expressao) {
        expressao = expressao.replaceAll("\\s+", "");

        int posicaoX = expressao.indexOf("x");

        if (posicaoX == -1) {
            throw new IllegalArgumentException(
                "Expressão deve conter a variável x"
            );
        }

        String parteAngular = expressao.substring(0, posicaoX);
        if (parteAngular.isEmpty() || parteAngular.equals("+")) {
            this.coeficienteAngular = 1.0;
        } else if (parteAngular.equals("-")) {
            this.coeficienteAngular = -1.0;
        } else {
            this.coeficienteAngular = Double.parseDouble(parteAngular);
        }

        String parteLinear = expressao.substring(posicaoX + 1);
        if (parteLinear.isEmpty()) {
            this.coeficienteLinear = 0.0;
        } else {
            this.coeficienteLinear = Double.parseDouble(parteLinear);
        }
    }

    private void inicializarPropriedades() {
        this.monotonia = coeficienteAngular > 0
            ? "função crescente"
            : "função decrescente";

        this.coeficientes.put("coeficiente_angular", coeficienteAngular);
        this.coeficientes.put("coeficiente_linear", coeficienteLinear);

        calcularRaizes();
    }

    @Override
    public double calcular(double x) {
        return coeficienteAngular * x + coeficienteLinear;
    }

    @Override
    public double limitar(double ponto, Tendencia tendencia) {
        return calcular(ponto);
    }

    @Override
    public Funcao derivar() {
        return new Afim(0.0, coeficienteAngular);
    }

    @Override
    public Funcao integrar() {
        return new Quadratica(coeficienteAngular / 2, coeficienteLinear, 0.0);
    }

    @Override
    public String getLeiFormacao() {
        StringBuilder sb = new StringBuilder();
        sb.append("f(x) = ");

        if (coeficienteAngular == 1.0) {
            sb.append("x");
        } else if (coeficienteAngular == -1.0) {
            sb.append("-x");
        } else {
            sb.append(coeficienteAngular).append("x");
        }

        if (coeficienteLinear > 0) {
            sb.append(" + ").append(coeficienteLinear);
        } else if (coeficienteLinear < 0) {
            sb.append(" - ").append(Math.abs(coeficienteLinear));
        }

        return sb.toString();
    }

    @Override
    public List<Double> getZeros() {
        return new ArrayList<>(raizes);
    }

    @Override
    public ParidadeEnum getParidade() {
        if (coeficienteLinear == 0) {
            return ParidadeEnum.IMPAR;
        }
        return ParidadeEnum.NENHUMA;
    }

    @Override
    public Map<Intervalo, String> getVariacaoSinal() {
        Map<Intervalo, String> variacao = new HashMap<>();

        if (raizes.isEmpty()) {
            return variacao;
        }

        double raiz = raizes.get(0);

        if (coeficienteAngular > 0) {
            variacao.put(new Intervalo("-∞", String.valueOf(raiz)), "negativo");
            variacao.put(new Intervalo(String.valueOf(raiz), "+∞"), "positivo");
        } else {
            variacao.put(new Intervalo("-∞", String.valueOf(raiz)), "positivo");
            variacao.put(new Intervalo(String.valueOf(raiz), "+∞"), "negativo");
        }

        return variacao;
    }

    @Override
    public Map<Intervalo, String> getMonotonia() {
        Map<Intervalo, String> monot = new HashMap<>();

        if (coeficienteAngular > 0) {
            monot.put(new Intervalo("-∞", "+∞"), "crescente");
        } else if (coeficienteAngular < 0) {
            monot.put(new Intervalo("-∞", "+∞"), "decrescente");
        } else {
            monot.put(new Intervalo("-∞", "+∞"), "constante");
        }

        return monot;
    }

    @Override
    public Map<Intervalo, String> getConcavidade() {
        Map<Intervalo, String> concav = new HashMap<>();
        concav.put(new Intervalo("-∞", "+∞"), "reta");
        return concav;
    }

    private void calcularRaizes() {
        if (coeficienteAngular != 0) {
            double raiz = -coeficienteLinear / coeficienteAngular;
            this.raizes.add(raiz);
        }
    }

    public Map<String, Object> getEstudoCompleto() {
        estudoCompleto.clear();
        estudoCompleto.put("lei_formacao", getLeiFormacao());
        estudoCompleto.put("raizes", getZeros());
        estudoCompleto.put("dominio", "ℝ (todos os números reais)");
        estudoCompleto.put("contradominio", "ℝ (todos os números reais)");
        estudoCompleto.put("coeficientes", coeficientes);
        estudoCompleto.put("grau", grau);
        estudoCompleto.put("monotonia", monotonia);
        estudoCompleto.put("paridade", getParidade().toString());
        estudoCompleto.put("variacao_sinal", getVariacaoSinal());
        estudoCompleto.put("continuidade", getContinuidade());

        return estudoCompleto;
    }

    public String getMonotoniaString() {
        return monotonia;
    }

    public Map<String, Object> getCoeficientesMap() {
        return new HashMap<>(coeficientes);
    }

    public Double getCoeficienteAngular() {
        return coeficienteAngular;
    }

    public void setCoeficienteAngular(Double coeficienteAngular) {
        this.coeficienteAngular = coeficienteAngular;
        inicializarPropriedades();
    }

    public Double getCoeficienteLinear() {
        return coeficienteLinear;
    }

    public void setCoeficienteLinear(Double coeficienteLinear) {
        this.coeficienteLinear = coeficienteLinear;
        inicializarPropriedades();
    }

    public List<Object> getRaizesAsObjects() {
        List<Object> raizesObj = new ArrayList<>();
        raizesObj.addAll(raizes);
        return raizesObj;
    }
}
