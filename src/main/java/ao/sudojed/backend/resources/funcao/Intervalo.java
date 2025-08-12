package ao.sudojed.backend.resources.funcao;

public class Intervalo {
    private String inicio;
    private String fim;
    public Intervalo(String inicio, String fim) {
        this.inicio = inicio;
        this.fim = fim;
    }
    @Override
    public String toString() {
        return "[" + inicio + ", " + fim + "]";
    }
}