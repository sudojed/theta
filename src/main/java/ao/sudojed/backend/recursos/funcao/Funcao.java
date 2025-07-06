package ao.sudojed.backend.recursos.funcao;

import java.util.HashMap;

public abstract class Funcao {
    private String leiFormacao;
    private String dominio;
    private String contradominio;
    private String zeros;
    private String paridade;
    private String variacaoSinal;
    private String continuidade;
    private HashMap<String, String> monotonia;
    private HashMap<String, String> concavidade;

    public abstract void limitar(int tendencia);

    public abstract void derivar();

    public abstract void integrar();

    // Getters and setters
    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getZeros() {
        return zeros;
    }

    public void setZeros(String zeros) {
        this.zeros = zeros;
    }

    public String getParidade() {
        return paridade;
    }

    public void setParidade(String paridade) {
        this.paridade = paridade;
    }

    public String getVariacaoSinal() {
        return variacaoSinal;
    }

    public void setVariacaoSinal(String variacaoSinal) {
        this.variacaoSinal = variacaoSinal;
    }

    public HashMap<String, String> getMonotonia() {
        return monotonia;
    }

    public void setMonotonia(HashMap<String, String> monotonia) {
        this.monotonia = monotonia;
    }

    public HashMap<String, String> getConcavidade() {
        return concavidade;
    }

    public void setConcavidade(HashMap<String, String> concavidade) {
        this.concavidade = concavidade;
    }

    public String getLeiFormacao() {
        return leiFormacao;
    }

    public void setLeiFormacao(String leiFormacao) {
        this.leiFormacao = leiFormacao;
    }

    public String getContradominio() {
        return contradominio;
    }

    public void setContradominio(String contradominio) {
        this.contradominio = contradominio;
    }
}
