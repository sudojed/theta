package ao.sudojed.backend.recursos.funcao;

import java.util.List;
import java.util.Map;

// Enum para Paridade
enum ParidadeEnum {
    PAR,
    IMPAR,
    NENHUMA
}

// Enum para Tendência (para limites)
enum Tendencia {
    MAIS_INFINITO,
    MENOS_INFINITO,
    FINITO // Para limites em um ponto específico
}

// Classe de suporte para representar intervalos (simplificado)
class Intervalo {
    private double inicio;
    private double fim;
    // Poderia ter flags para inclusão/exclusão dos limites
    public Intervalo(double inicio, double fim) {
        this.inicio = inicio;
        this.fim = fim;
    }
    // Getters
    @Override
    public String toString() {
        return "[" + inicio + ", " + fim + "]";
    }
}

public abstract class Funcao {
    protected String leiFormacao; // Continua como String para representação
    protected String continuidade; // Pode ser um Enum mais tarde

    // Mudanças de String para tipos mais específicos
    protected List<Intervalo> dominio;
    protected List<Intervalo> contradominio; // Pode ser List<Intervalo> ou String dependendo da complexidade
    protected List<Double> zeros; // Lista de valores dos zeros
    protected ParidadeEnum paridade; // Enum para paridade
    protected Map<Intervalo, String> variacaoSinal; // Map de Intervalo para "Positivo", "Negativo"
    protected Map<Intervalo, String> monotonia; // Map de Intervalo para "Crescente", "Decrescente"
    protected Map<Intervalo, String> concavidade; // Map de Intervalo para "Concava Para Cima", "Concava Para Baixo"

    // Protected constructor para inicializar atributos comuns
    protected Funcao(String leiFormacao) {
        this.leiFormacao = leiFormacao;
    }

    // Métodos abstratos (retornando um novo Funcao para Derivar/Integrar)
    public abstract double calcular(double x); // Adicionei este para ser fundamental
    public abstract double limitar(double ponto, Tendencia tendencia); // Exemplo de como pode ser mais específico
    public abstract Funcao derivar(); // Retorna uma nova função derivada
    public abstract Funcao integrar(); // Retorna uma nova função integral

    // Getters and setters (ajustados para os novos tipos)
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
    // Setter for leiFormacao might not be needed if it's derived from constructor

    public List<Intervalo> getContradominio() { return contradominio; }
    public void setContradominio(List<Intervalo> contradominio) { this.contradominio = contradominio; }
}