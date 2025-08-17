package ao.sudojed.backend;

import static org.junit.jupiter.api.Assertions.*;

import ao.sudojed.backend.recursos.funcao.enums.ParidadeEnum;
import ao.sudojed.backend.recursos.funcao.enums.Tendencia;
import ao.sudojed.backend.recursos.funcao.polinomial.Afim;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AfimTest {

    private Afim funcaoAfim;

    @BeforeEach
    void setUp() {
        funcaoAfim = new Afim(2.0, 3.0);
    }

    @Test
    void testConstrutor() {
        assertEquals(2.0, funcaoAfim.getCoeficienteAngular());
        assertEquals(3.0, funcaoAfim.getCoeficienteLinear());
        assertEquals(1, funcaoAfim.getGrau());
    }

    @Test
    void testConstrutorComString() {
        Afim afim1 = new Afim("2x+3");
        assertEquals(2.0, afim1.getCoeficienteAngular());
        assertEquals(3.0, afim1.getCoeficienteLinear());

        Afim afim2 = new Afim("x-5");
        assertEquals(1.0, afim2.getCoeficienteAngular());
        assertEquals(-5.0, afim2.getCoeficienteLinear());

        Afim afim3 = new Afim("-3x+7");
        assertEquals(-3.0, afim3.getCoeficienteAngular());
        assertEquals(7.0, afim3.getCoeficienteLinear());
    }

    @Test
    void testCalcular() {
        // f(x) = 2x + 3
        assertEquals(7.0, funcaoAfim.calcular(2.0)); // 2*2 + 3 = 7
        assertEquals(3.0, funcaoAfim.calcular(0.0)); // 2*0 + 3 = 3
        assertEquals(1.0, funcaoAfim.calcular(-1.0)); // 2*(-1) + 3 = 1
    }

    @Test
    void testLeiFormacao() {
        assertEquals("2.0x+3.0", funcaoAfim.getLeiFormacao());

        Afim afim2 = new Afim(1.0, -2.0);
        assertEquals("x-2.0", afim2.getLeiFormacao());

        Afim afim3 = new Afim(-1.0, 0.0);
        assertEquals("-x", afim3.getLeiFormacao());
    }

    @Test
    void testCalcularRaizes() {
        List<Double> raizes = funcaoAfim.getZeros();
        assertEquals(1, raizes.size());
        assertEquals(-1.5, raizes.get(0), 0.001);
    }

    @Test
    void testParidade() {
        assertEquals(ParidadeEnum.NENHUMA, funcaoAfim.getParidade());

        Afim afimImpar = new Afim(2.0, 0.0);
        assertEquals(ParidadeEnum.IMPAR, afimImpar.getParidade());
    }

    @Test
    void testMonotonia() {
        Map<String, Object> estudo = funcaoAfim.getEstudoCompleto();
        assertEquals("crescente", estudo.get("monotonaSimples"));

        Afim afimDecrescente = new Afim(-2.0, 5.0);
        Map<String, Object> estudoDecrescente =
            afimDecrescente.getEstudoCompleto();
        assertEquals("decrescente", estudoDecrescente.get("monotonaSimples"));
    }

    @Test
    void testDerivar() {
        Afim derivada = (Afim) funcaoAfim.derivar();
        assertEquals(0.0, derivada.getCoeficienteAngular());
        assertEquals(2.0, derivada.getCoeficienteLinear());
    }

    @Test
    void testEstudoCompleto() {
        Map<String, Object> estudo = funcaoAfim.getEstudoCompleto();

        assertNotNull(estudo.get("leiFormacao"));
        assertNotNull(estudo.get("zeros"));
        assertNotNull(estudo.get("dominio"));
        assertNotNull(estudo.get("coeficientes"));
        assertEquals(1, estudo.get("grau"));
        assertEquals("crescente", estudo.get("monotonaSimples"));
    }

    @Test
    void testVariacaoSinal() {
        Map<?, String> variacao = funcaoAfim.getVariacaoSinal();
        assertFalse(variacao.isEmpty());
    }

    @Test
    void testLimitar() {
        assertEquals(7.0, funcaoAfim.limitar(2.0, null));
    }
}
