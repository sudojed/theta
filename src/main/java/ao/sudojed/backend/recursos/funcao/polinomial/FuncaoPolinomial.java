package ao.sudojed.backend.recursos.funcao.polinomial;

import ao.sudojed.backend.recursos.funcao.Funcao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FuncaoPolinomial extends Funcao {

    protected int grau;
    protected Map<String, Double> coeficientes;

    protected FuncaoPolinomial(String leiFormacao, int grau) {
        super(leiFormacao);
        this.grau = grau;
        this.coeficientes = new HashMap<>();
        this.dominio = new ArrayList<>();
        this.contradominio = new ArrayList<>();
        this.zeros = new ArrayList<>();
        this.variacaoSinal = new HashMap<>();
        this.monotonia = new HashMap<>();
        this.concavidade = new HashMap<>();

        // Domínio de função polinomial é sempre R (todos os reais)
        this.dominio.add(
            new Funcao.Intervalo(
                Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY
            )
        );
        this.continuidade = "Contínua em R";
    }

    public int getGrau() {
        return grau;
    }

    public Map<String, Double> getCoeficientes() {
        return coeficientes;
    }

    protected abstract void calcularZeros();

    protected abstract void calcularMonotonia();

    protected abstract void calcularVariacaoSinal();

    public Map<String, Object> getEstudoCompleto() {
        Map<String, Object> estudo = new HashMap<>();
        estudo.put("leiFormacao", leiFormacao);
        estudo.put("grau", grau);
        estudo.put("dominio", "R");
        estudo.put("contradominio", contradominio);
        estudo.put("zeros", zeros);
        estudo.put("coeficientes", coeficientes);
        estudo.put("paridade", paridade);
        estudo.put("monotonia", monotonia);
        estudo.put("variacaoSinal", variacaoSinal);
        estudo.put("concavidade", concavidade);
        estudo.put("continuidade", continuidade);
        return estudo;
    }
}
