package ao.sudojed.backend.recursos.funcao.polinomial;

import ao.sudojed.backend.recursos.funcao.enums.ParidadeEnum;
import java.util.Map;

/**
 * Classe de demonstração para a funcionalidade da classe Afim.
 * Esta classe mostra como usar a implementação de funções afins (lineares)
 * baseada no contexto original do LinearEquation.
 */
public class AfimDemo {

    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DA CLASSE AFIM ===\n");

        // Exemplo 1: Função f(x) = 2x + 3
        System.out.println("1. Função f(x) = 2x + 3");
        Afim funcao1 = new Afim("2x+3");
        demonstrarFuncao(funcao1);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Exemplo 2: Função f(x) = -x + 5
        System.out.println("2. Função f(x) = -x + 5");
        Afim funcao2 = new Afim("-x+5");
        demonstrarFuncao(funcao2);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Exemplo 3: Função f(x) = 3x (função ímpar)
        System.out.println("3. Função f(x) = 3x (função ímpar)");
        Afim funcao3 = new Afim(3, 0);
        demonstrarFuncao(funcao3);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Exemplo 4: Função constante f(x) = 5
        System.out.println("4. Função constante f(x) = 5");
        Afim funcaoConstante = new Afim(0, 5);
        demonstrarFuncao(funcaoConstante);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstração de cálculos
        demonstrarCalculos();

        // Demonstração de derivadas
        demonstrarDerivadas();
    }

    /**
     * Demonstra as propriedades básicas de uma função afim
     */
    private static void demonstrarFuncao(Afim funcao) {
        System.out.println("Lei de formação: " + funcao.getLeiFormacao());
        System.out.println("Coeficiente angular (a): " + funcao.getCoeficienteAngular());
        System.out.println("Coeficiente linear (b): " + funcao.getCoeficienteLinear());
        System.out.println("Grau: " + funcao.getGrau());
        System.out.println("Zeros da função: " + funcao.getZeros());
        System.out.println("Monotonia: " + funcao.getMonotoniaSimples());
        System.out.println("Paridade: " + funcao.getParidade());

        // Calcular alguns valores
        System.out.println("Valores calculados:");
        System.out.println("  f(-2) = " + funcao.calcular(-2));
        System.out.println("  f(0) = " + funcao.calcular(0));
        System.out.println("  f(2) = " + funcao.calcular(2));
    }

    /**
     * Demonstra cálculos específicos com diferentes funções
     */
    private static void demonstrarCalculos() {
        System.out.println("DEMONSTRAÇÃO DE CÁLCULOS ESPECÍFICOS\n");

        Afim funcao = new Afim("2x-4");
        System.out.println("Para a função f(x) = " + funcao.getLeiFormacao() + ":");

        // Encontrar onde a função é zero
        double zero = funcao.getZeros().get(0);
        System.out.println("Zero da função: x = " + zero);
        System.out.println("Verificação: f(" + zero + ") = " + funcao.calcular(zero));

        // Encontrar onde a função vale 10
        // Se f(x) = 2x - 4 = 10, então 2x = 14, x = 7
        double x = 7;
        System.out.println("Para f(x) = 10: x = " + x);
        System.out.println("Verificação: f(" + x + ") = " + funcao.calcular(x));

        // Análise de crescimento/decrescimento
        System.out.println("Análise de monotonia:");
        System.out.println("  f(1) = " + funcao.calcular(1));
        System.out.println("  f(2) = " + funcao.calcular(2));
        System.out.println("  f(3) = " + funcao.calcular(3));
        System.out.println("  A função é " + funcao.getMonotoniaSimples());
    }

    /**
     * Demonstra o cálculo de derivadas
     */
    private static void demonstrarDerivadas() {
        System.out.println("\nDEMONSTRAÇÃO DE DERIVADAS\n");

        Afim funcao1 = new Afim("3x+7");
        System.out.println("Função original: f(x) = " + funcao1.getLeiFormacao());

        Afim derivada1 = (Afim) funcao1.derivar();
        System.out.println("Derivada: f'(x) = " + derivada1.getLeiFormacao());
        System.out.println("Valor da derivada (constante): " + derivada1.getCoeficienteLinear());

        System.out.println();

        Afim funcao2 = new Afim("-2x+5");
        System.out.println("Função original: g(x) = " + funcao2.getLeiFormacao());

        Afim derivada2 = (Afim) funcao2.derivar();
        System.out.println("Derivada: g'(x) = " + derivada2.getLeiFormacao());
        System.out.println("Valor da derivada (constante): " + derivada2.getCoeficienteLinear());
    }

    /**
     * Demonstra o estudo completo de uma função
     */
    public static void estudoCompletoExemplo() {
        System.out.println("\nESTUDO COMPLETO DE UMA FUNÇÃO AFIM\n");

        Afim funcao = new Afim("4x-8");
        Map<String, Object> estudo = funcao.getEstudoCompleto();

        System.out.println("ESTUDO COMPLETO DA FUNÇÃO f(x) = " + funcao.getLeiFormacao());
        System.out.println("=" .repeat(60));

        estudo.forEach((chave, valor) -> {
            System.out.println(chave + ": " + valor);
        });

        System.out.println("\nANÁLISE GRÁFICA:");
        System.out.println("- A função corta o eixo y no ponto (0, " + funcao.getCoeficienteLinear() + ")");
        if (!funcao.getZeros().isEmpty()) {
            double zero = funcao.getZeros().get(0);
            System.out.println("- A função corta o eixo x no ponto (" + zero + ", 0)");
        }
        System.out.println("- A reta tem inclinação " +
            (funcao.getCoeficienteAngular() > 0 ? "positiva" : "negativa"));
    }

    /**
     * Compara diferentes tipos de funções afins
     */
    public static void compararFuncoes() {
        System.out.println("\nCOMPARAÇÃO ENTRE DIFERENTES FUNÇÕES AFINS\n");

        Afim crescente = new Afim("2x+1");
        Afim decrescente = new Afim("-3x+5");
        Afim constante = new Afim(0, 7);
        Afim impar = new Afim(4, 0);

        System.out.println("Função crescente: " + crescente.getLeiFormacao() +
            " (monotonia: " + crescente.getMonotoniaSimples() + ")");
        System.out.println("Função decrescente: " + decrescente.getLeiFormacao() +
            " (monotonia: " + decrescente.getMonotoniaSimples() + ")");
        System.out.println("Função constante: " + constante.getLeiFormacao() +
            " (monotonia: " + constante.getMonotoniaSimples() + ")");
        System.out.println("Função ímpar: " + impar.getLeiFormacao() +
            " (paridade: " + impar.getParidade() + ")");

        System.out.println("\nComparando valores em x = 2:");
        System.out.println("  Crescente: f(2) = " + crescente.calcular(2));
        System.out.println("  Decrescente: f(2) = " + decrescente.calcular(2));
        System.out.println("  Constante: f(2) = " + constante.calcular(2));
        System.out.println("  Ímpar: f(2) = " + impar.calcular(2));
    }
}
