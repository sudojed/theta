package ao.sudojed.backend.examples;

import ao.sudojed.backend.recursos.funcao.Funcao;
import ao.sudojed.backend.recursos.funcao.polinomial.Afim;
import java.util.Map;

public class AfimExample {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLO DE USO DA CLASSE AFIM ===\n");

        System.out.println("1. Criação da função f(x) = 2x + 3");
        Afim funcao1 = new Afim(2.0, 3.0);
        demonstrarFuncao(funcao1);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println(
            "2. Criação da função g(x) = -3x + 5 a partir de string"
        );
        Afim funcao2 = new Afim("-3x+5");
        demonstrarFuncao(funcao2);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("3. Função ímpar h(x) = 4x");
        Afim funcao3 = new Afim("4x");
        demonstrarFuncao(funcao3);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("4. Operações matemáticas com f(x) = 2x + 3");
        demonstrarOperacoes(funcao1);
    }

    private static void demonstrarFuncao(Afim funcao) {
        System.out.println("Lei de formação: " + funcao.getLeiFormacao());
        System.out.println("Grau: " + funcao.getGrau());
        System.out.println(
            "Coeficiente angular: " + funcao.getCoeficienteAngular()
        );
        System.out.println(
            "Coeficiente linear: " + funcao.getCoeficienteLinear()
        );
        System.out.println("Monotonia: " + funcao.getMonotoniaSimples());
        System.out.println("Paridade: " + funcao.getParidade());
        System.out.println("Zeros da função: " + funcao.getZeros());
        System.out.println("Domínio: " + funcao.getDominio());
        System.out.println("Continuidade: " + funcao.getContinuidade());

        System.out.println("\nTeste de valores:");
        double[] valores = { -2, -1, 0, 1, 2 };
        for (double x : valores) {
            System.out.printf("f(%.0f) = %.2f\n", x, funcao.calcular(x));
        }

        System.out.println("\nVariação de sinal: " + funcao.getVariacaoSinal());
        System.out.println("Monotonia (intervalos): " + funcao.getMonotonia());
    }

    private static void demonstrarOperacoes(Afim funcao) {
        System.out.println("Função original: " + funcao.getLeiFormacao());

        Funcao derivada = funcao.derivar();
        System.out.println("Derivada: f'(x) = " + derivada.getLeiFormacao());
        System.out.println("Valor da derivada em x=5: " + derivada.calcular(5));

        Funcao integral = funcao.integrar();
        System.out.println("Integral: F(x) = " + integral.getLeiFormacao());
        System.out.println("Valor da integral em x=2: " + integral.calcular(2));

        double pontoLimite = 3.0;
        double limite = funcao.limitar(pontoLimite, null);
        System.out.printf(
            "Limite quando x tende a %.1f: %.2f\n",
            pontoLimite,
            limite
        );

        System.out.println("\n=== ESTUDO COMPLETO ===");
        Map<String, Object> estudo = funcao.getEstudoCompleto();
        estudo.forEach((chave, valor) -> {
            System.out.println(chave + ": " + valor);
        });
    }
}
