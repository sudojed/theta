package ao.sudojed.backend.recursos.funcao;

import java.util.List;
import java.util.Map;

import ao.sudojed.backend.recursos.funcao.enums.ParidadeEnum;
import ao.sudojed.backend.recursos.funcao.enums.Tendencia;



class Intervalo {
    private double inicio;
    private double fim;
    public Intervalo(double inicio, double fim) {
        this.inicio = inicio;
        this.fim = fim;
    }
    @Override
    public String toString() {
        return "[" + inicio + ", " + fim + "]";
    }
}

public abstract class Funcao {
    protected String leiFormacao;
    protected String continuidade;
    protected List<Intervalo> dominio;
    protected List<Intervalo> contradominio;
    protected List<Double> zeros;
    protected ParidadeEnum paridade;
    protected Map<Intervalo, String> variacaoSinal;
    protected Map<Intervalo, String> monotonia;
    protected Map<Intervalo, String> concavidade;

    protected Funcao(String leiFormacao) {
        this.leiFormacao = leiFormacao;
    }

    public abstract double calcular(double x);
    public abstract double limitar(double ponto, Tendencia tendencia);
    public abstract Funcao derivar();
    public abstract Funcao integrar();

    public List<Intervalo> getDominio() { return dominio; }
    public void setDominio(List<Intervalo> dominio) { this.dominio = dominio; }

    public List<Double> getZeros() { return zeros; }
    public void setZeros(List<Double> zeros) { this.zeros = zeros; }

    public ParidadeEnum getParidade() { return paridade; }
    public void setParidade(ParidadeEnum paridade) { this.paridade = paridade; }

    public Map<Intervalo, String> getVariacaoSinal() { return variacaoSinal; }
    public void setVariacaoSinal(Map<Intervalo, String> variacaoSinal) { this.variacaoSinal = variacaoSinal; }

    public Map<Intervalo, String> getMonotonia() { return monotonia; }
    public void setMonotonia(Map<Intervalo, String> monotonia) { this.monotonia = monotonia; }

    public Map<Intervalo, String> getConcavidade() { return concavidade; }
    public void setConcavidade(Map<Intervalo, String> concavidade) { this.concavidade = concavidade; }

    public String getLeiFormacao() { return leiFormacao; }

    public List<Intervalo> getContradominio() { return contradominio; }
    public void setContradominio(List<Intervalo> contradominio) { this.contradominio = contradominio; }
}
