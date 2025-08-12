package ao.sudojed.backend.resources.funcao;

import ao.sudojed.backend.resources.funcao.enums.ParidadeEnum;
import ao.sudojed.backend.resources.funcao.enums.Tendencia;
import java.util.List;
import java.util.Map;

public interface Funcao {
    // Métodos abstratos obrigatórios
    public abstract double calcular(double x);
    public abstract double limitar(double ponto, Tendencia tendencia);
    public abstract Funcao derivar();
    public abstract Funcao integrar();

    // Métodos default com implementações padrão (podem ser sobrescritos)
    default String getLeiFormacao() {
        return "Não definida";
    }

    default String getContinuidade() {
        return "Contínua em todo domínio";
    }

    default List<Intervalo> getDominio() {
        // Domínio padrão: todos os números reais
        return List.of(new Intervalo("-∞", "+∞"));
    }

    default List<Intervalo> getContradominio() {
        // Contradomínio padrão: todos os números reais
        return List.of(new Intervalo("-∞", "+∞"));
    }

    default List<Double> getZeros() {
        // Por padrão, zeros não são calculados automaticamente
        return List.of();
    }

    default ParidadeEnum getParidade() {
        return ParidadeEnum.NENHUMA;
    }

    default Map<Intervalo, String> getVariacaoSinal() {
        return Map.of();
    }

    default Map<Intervalo, String> getMonotonia() {
        return Map.of();
    }

    default Map<Intervalo, String> getConcavidade() {
        return Map.of();
    }
}
