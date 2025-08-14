package ao.sudojed.backend.recursos.funcao.polinomial;

import ao.sudojed.backend.recursos.funcao.Funcao;
import ao.sudojed.backend.recursos.funcao.enums.ParidadeEnum;
import ao.sudojed.backend.recursos.funcao.enums.Tendencia;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private double coeficienteAngular; // a
    private double coeficienteLinear; // b

    public Afim(String expressao) {
        super(parseExpressao(expressao), 1);
        parseCoeficientes(expressao);
        inicializarFuncao();
    }

    public Afim(double coeficienteAngular, double coeficienteLinear) {
        super(montarLeiFormacao(coeficienteAngular, coeficienteLinear), 1);
        this.coeficienteAngular = coeficienteAngular;
        this.coeficienteLinear = coeficienteLinear;
        inicializarFuncao();
    }

    private static String parseExpressao(String expressao) {
        // Remove espaços em branco
        expressao = expressao.replaceAll("\\s+", "");

        // Padrão para capturar ax+b ou ax-b
        Pattern pattern = Pattern.compile(
            "([+-]?\\d*\\.?\\d*)x([+-]\\d*\\.?\\d*)"
        );
        Matcher matcher = pattern.matcher(expressao);

        if (matcher.find()) {
            String aStr = matcher.group(1);
            String bStr = matcher.group(2);

            // Se não há coeficiente antes do x, assume 1
            if (aStr.isEmpty() || aStr.equals("+")) {
                aStr = "1";
            } else if (aStr.equals("-")) {
                aStr = "-1";
            }

            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            return montarLeiFormacao(a, b);
        }

        throw new IllegalArgumentException("Expressão inválida: " + expressao);
    }

    private void parseCoeficientes(String expressao) {
        // Remove espaços em branco
        expressao = expressao.replaceAll("\\s+", "");

        // Padrão para capturar ax+b ou ax-b
        Pattern pattern = Pattern.compile(
            "([+-]?\\d*\\.?\\d*)x([+-]\\d*\\.?\\d*)"
        );
        Matcher matcher = pattern.matcher(expressao);

        if (matcher.find()) {
            String aStr = matcher.group(1);
            String bStr = matcher.group(2);

            // Se não há coeficiente antes do x, assume 1
            if (aStr.isEmpty() || aStr.equals("+")) {
                aStr = "1";
            } else if (aStr.equals("-")) {
                aStr = "-1";
            }

            this.coeficienteAngular = Double.parseDouble(aStr);
            this.coeficienteLinear = Double.parseDouble(bStr);
        } else {
            throw new IllegalArgumentException(
                "Expressão inválida: " + expressao
            );
        }
    }

    private static String montarLeiFormacao(double a, double b) {
        StringBuilder sb = new StringBuilder();

        if (a == 1) {
            sb.append("x");
        } else if (a == -1) {
            sb.append("-x");
        } else {
            sb.append(a).append("x");
        }

        if (b > 0) {
            sb.append("+").append(b);
        } else if (b < 0) {
            sb.append(b);
        }

        return sb.toString();
    }

    private void inicializarFuncao() {
        // Inicializar coeficientes
        this.coeficientes.put("a", coeficienteAngular);
        this.coeficientes.put("b", coeficienteLinear);

        // Calcular propriedades da função
        calcularZeros();
        calcularMonotonia();
        calcularVariacaoSinal();
        determinarParidade();

        // Contradomínio é R para função afim
        this.contradominio = new ArrayList<>();
        this.contradominio.add(
            new Funcao.Intervalo(
                Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY
            )
        );
    }

    @Override
    protected void calcularZeros() {
        if (coeficienteAngular != 0) {
            double zero = -coeficienteLinear / coeficienteAngular;
            this.zeros.add(zero);
        }
        // Se coeficienteAngular = 0, não há zeros (função constante) ou todos são zeros
    }

    @Override
    protected void calcularMonotonia() {
        this.monotonia.clear();

        if (coeficienteAngular > 0) {
            // Função crescente
            this.monotonia.put(
                new Funcao.Intervalo(
                    Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY
                ),
                "crescente"
            );
        } else if (coeficienteAngular < 0) {
            // Função decrescente
            this.monotonia.put(
                new Funcao.Intervalo(
                    Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY
                ),
                "decrescente"
            );
        } else {
            // Função constante
            this.monotonia.put(
                new Funcao.Intervalo(
                    Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY
                ),
                "constante"
            );
        }
    }

    @Override
    protected void calcularVariacaoSinal() {
        this.variacaoSinal.clear();

        if (coeficienteAngular == 0) {
            // Função constante
            String sinal = coeficienteLinear > 0
                ? "positiva"
                : coeficienteLinear < 0 ? "negativa" : "zero";
            this.variacaoSinal.put(
                new Funcao.Intervalo(
                    Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY
                ),
                sinal
            );
        } else if (!zeros.isEmpty()) {
            double zero = zeros.get(0);

            if (coeficienteAngular > 0) {
                // Função crescente: negativa antes do zero, positiva depois
                this.variacaoSinal.put(
                    new Funcao.Intervalo(Double.NEGATIVE_INFINITY, zero),
                    "negativa"
                );
                this.variacaoSinal.put(
                    new Funcao.Intervalo(zero, Double.POSITIVE_INFINITY),
                    "positiva"
                );
            } else {
                // Função decrescente: positiva antes do zero, negativa depois
                this.variacaoSinal.put(
                    new Funcao.Intervalo(Double.NEGATIVE_INFINITY, zero),
                    "positiva"
                );
                this.variacaoSinal.put(
                    new Funcao.Intervalo(zero, Double.POSITIVE_INFINITY),
                    "negativa"
                );
            }
        }
    }

    private void determinarParidade() {
        // Função afim f(x) = ax + b é par apenas se a = 0 e b qualquer
        // Função afim f(x) = ax + b é ímpar apenas se b = 0
        if (coeficienteAngular == 0) {
            this.paridade = ParidadeEnum.PAR; // Função constante é par
        } else if (coeficienteLinear == 0) {
            this.paridade = ParidadeEnum.IMPAR; // f(x) = ax é ímpar
        } else {
            this.paridade = ParidadeEnum.NENHUMA; // Função geral não é par nem ímpar
        }
    }

    @Override
    public double calcular(double x) {
        return coeficienteAngular * x + coeficienteLinear;
    }

    @Override
    public double limitar(double ponto, Tendencia tendencia) {
        // Para função afim, o limite sempre existe e é igual ao valor da função
        return calcular(ponto);
    }

    @Override
    public Funcao derivar() {
        // A derivada de ax + b é a (constante)
        return new Afim(0, coeficienteAngular);
    }

    @Override
    public Funcao integrar() {
        // A integral de ax + b é (a/2)x² + bx + C
        // Retornamos uma função quadrática (para simplificar, C = 0)
        // Como não temos Quadratica implementada, retornamos uma representação simplificada
        throw new UnsupportedOperationException(
            "Integração não implementada ainda - requer classe Quadratica"
        );
    }

    // Getters específicos para função afim
    public double getCoeficienteAngular() {
        return coeficienteAngular;
    }

    public double getCoeficienteLinear() {
        return coeficienteLinear;
    }

    public String getMonotoniaSimples() {
        if (coeficienteAngular > 0) {
            return "crescente";
        } else if (coeficienteAngular < 0) {
            return "decrescente";
        } else {
            return "constante";
        }
    }

    @Override
    public Map<String, Object> getEstudoCompleto() {
        Map<String, Object> estudo = super.getEstudoCompleto();
        estudo.put("coeficienteAngular", coeficienteAngular);
        estudo.put("coeficienteLinear", coeficienteLinear);
        estudo.put("monotonaSimples", getMonotoniaSimples());
        return estudo;
    }

    @Override
    public String toString() {
        return "Função Afim: f(x) = " + leiFormacao;
    }
}
