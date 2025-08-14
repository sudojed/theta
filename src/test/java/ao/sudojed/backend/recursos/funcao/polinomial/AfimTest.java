package ao.sudojed.backend.recursos.funcao.polinomial;

import ao.sudojed.backend.recursos.funcao.enums.ParidadeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class AfimTest {

    private Afim funcaoAfim1;
    private Afim funcaoAfim2;
    private Afim funcaoAfim3;

    @BeforeEach
    void setUp() {
        // f(x) = 2x + 3
        funcaoAfim1 = new Afim("2x+3");

        // f(x) = -x + 5
        funcaoAfim2 = new Afim("-x+5");

        // f(x) = 3x (função ímpar)
        funcaoAfim3 = new Afim(3, 0);
    }

    @Test
    void testConstrutorComExpressao() {
        assertEquals(2.0, funcaoAfim1.getCoeficienteAngular());
        assertEquals(3.0, funcaoAfim1.getCoeficienteLinear());
        assertEquals("2.0x+3.0", funcaoAfim1.getLeiFormacao());
    }

    @Test
    void testConstrutorComCoeficientes() {
        assertEquals(3.0, funcaoAfim3.getCoeficienteAngular());
        assertEquals(0.0, funcaoAfim3.getCoeficienteLinear());
        assertEquals("3.0x", funcaoAfim3.getLeiFormacao());
    }

    @Test
    void testCalcularValor() {
        // f(x) = 2x + 3
        assertEquals(7.0, funcaoAfim1.calcular(2.0)); // 2*2 + 3 = 7
        assertEquals(3.0, funcaoAfim1.calcular(0.0)); // 2*0 + 3 = 3
        assertEquals(1.0, funcaoAfim1.calcular(-1.0)); // 2*(-1) + 3 = 1

        // f(x) = -x + 5
        assertEquals(3.0, funcaoAfim2.calcular(2.0)); // -2 + 5 = 3
        assertEquals(5.0, funcaoAfim2.calcular(0.0)); // 0 + 5 = 5
    }

    @Test
    void testCalcularZeros() {
        // f(x) = 2x + 3, zero em x = -3/2 = -1.5
        assertEquals(1, funcaoAfim1.getZeros().size());
        assertEquals(-1.5, funcaoAfim1.getZeros().get(0), 0.001);

        // f(x) = -x + 5, zero em x = 5
        assertEquals(1, funcaoAfim2.getZeros().size());
        assertEquals(5.0, funcaoAfim2.getZeros().get(0), 0.001);

        // f(x) = 3x, zero em x = 0
        assertEquals(1, funcaoAfim3.getZeros().size());
        assertEquals(0.0, funcaoAfim3.getZeros().get(0), 0.001);
    }

    @Test
    void testMonotonia() {
        // f(x) = 2x + 3 (a > 0, crescente)
        assertEquals("crescente", funcaoAfim1.getMonotoniaSimples());

        // f(x) = -x + 5 (a < 0, decrescente)
        assertEquals("decrescente", funcaoAfim2.getMonotoniaSimples());

        // f(x) = 3x (a > 0, crescente)
        assertEquals("crescente", funcaoAfim3.getMonotoniaSimples());
    }

    @Test
    void testParidade() {
        // f(x) = 2x + 3 (nem par nem ímpar)
        assertEquals(ParidadeEnum.NENHUMA, funcaoAfim1.getParidade());

        // f(x) = -x + 5 (nem par nem ímpar)
        assertEquals(ParidadeEnum.NENHUMA, funcaoAfim2.getParidade());

        // f(x) = 3x (função ímpar, pois b = 0)
        assertEquals(ParidadeEnum.IMPAR, funcaoAfim3.getParidade());
    }

    @Test
    void testFuncaoConstante() {
        Afim funcaoConstante = new Afim(0, 5); // f(x) = 5

        assertEquals(0.0, funcaoConstante.getCoeficienteAngular());
        assertEquals(5.0, funcaoConstante.getCoeficienteLinear());
        assertEquals("constante", funcaoConstante.getMonotoniaSimples());
        assertEquals(ParidadeEnum.PAR, funcaoConstante.getParidade());

        // Função constante tem o mesmo valor para qualquer x
        assertEquals(5.0, funcaoConstante.calcular(0.0));
        assertEquals(5.0, funcaoConstante.calcular(10.0));
        assertEquals(5.0, funcaoConstante.calcular(-5.0));
    }

    @Test
    void testDerivada() {
        // A derivada de f(x) = 2x + 3 é f'(x) = 2
        Afim derivada = (Afim) funcaoAfim1.derivar();
        assertEquals(0.0, derivada.getCoeficienteAngular()); // Derivada é constante
        assertEquals(2.0, derivada.getCoeficienteLinear()); // Valor da constante
    }

    @Test
    void testLimite() {
        // Para função afim, limite sempre existe e é igual ao valor da função
        assertEquals(7.0, funcaoAfim1.limitar(2.0, null));
        assertEquals(3.0, funcaoAfim2.limitar(2.0, null));
    }

    @Test
    void testGrau() {
        assertEquals(1, funcaoAfim1.getGrau());
        assertEquals(1, funcaoAfim2.getGrau());
        assertEquals(1, funcaoAfim3.getGrau());
    }

    @Test
    void testCoeficientes() {
        Map<String, Double> coeficientes = funcaoAfim1.getCoeficientes();
        assertEquals(2.0, coeficientes.get("a"));
        assertEquals(3.0, coeficientes.get("b"));
    }

    @Test
    void testEstudoCompleto() {
        Map<String, Object> estudo = funcaoAfim1.getEstudoCompleto();

        assertNotNull(estudo.get("leiFormacao"));
        assertEquals(1, estudo.get("grau"));
        assertEquals("R", estudo.get("dominio"));
        assertNotNull(estudo.get("zeros"));
        assertNotNull(estudo.get("coeficientes"));
        assertEquals(2.0, estudo.get("coeficienteAngular"));
        assertEquals(3.0, estudo.get("coeficienteLinear"));
        assertEquals("crescente", estudo.get("monotonaSimples"));
    }

    @Test
    void testExpressoesVariadas() {
        // Teste diferentes formatos de expressão
        Afim f1 = new Afim("x+1");    // Coeficiente angular implícito = 1
        assertEquals(1.0, f1.getCoeficienteAngular());
        assertEquals(1.0, f1.getCoeficienteLinear());

        Afim f2 = new Afim("-x+2");   // Coeficiente angular implícito = -1
        assertEquals(-1.0, f2.getCoeficienteAngular());
        assertEquals(2.0, f2.getCoeficienteLinear());

        Afim f3 = new Afim("5x-3");   // Formato padrão
        assertEquals(5.0, f3.getCoeficienteAngular());
        assertEquals(-3.0, f3.getCoeficienteLinear());
    }

    @Test
    void testExpressaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Afim("2y + 3"); // Variável diferente de x
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Afim("abc"); // Expressão completamente inválida
        });
    }

    @Test
    void testToString() {
        String resultado = funcaoAfim1.toString();
        assertTrue(resultado.contains("Função Afim"));
        assertTrue(resultado.contains("f(x)"));
    }
}
