package ao.sudojed.backend.resources.funcao.polinomial;

import ao.sudojed.backend.resources.funcao.Funcao;
import ao.sudojed.backend.resources.funcao.enums.Tendencia;

public class Quadratica implements Funcao {

    private Double coeficienteQuadratico;
    private Double coeficienteLinear;
    private Double termoIndependente;

    public Quadratica(
        Double coeficienteQuadratico,
        Double coeficienteLinear,
        Double termoIndependente
    ) {
        this.coeficienteQuadratico = coeficienteQuadratico;
        this.coeficienteLinear = coeficienteLinear;
        this.termoIndependente = termoIndependente;
    }

    @Override
    public double calcular(double x) {
        return (
            coeficienteQuadratico * x * x +
            coeficienteLinear * x +
            termoIndependente
        );
    }

    @Override
    public double limitar(double ponto, Tendencia tendencia) {
        return calcular(ponto);
    }

    @Override
    public Funcao derivar() {
        return new Afim(2 * coeficienteQuadratico, coeficienteLinear);
    }

    @Override
    public Funcao integrar() {
        final double a = coeficienteQuadratico;
        final double b = coeficienteLinear;
        final double c = termoIndependente;

        return new Funcao() {
            @Override
            public double calcular(double x) {
                return (a / 3) * x * x * x + (b / 2) * x * x + c * x;
            }

            @Override
            public double limitar(double ponto, Tendencia tendencia) {
                return calcular(ponto);
            }

            @Override
            public Funcao derivar() {
                return new Quadratica(a, b, c);
            }

            @Override
            public Funcao integrar() {
                throw new UnsupportedOperationException(
                    "Integração de ordem superior não implementada"
                );
            }
        };
    }

    public Double getCoeficienteQuadratico() {
        return coeficienteQuadratico;
    }

    public void setCoeficienteQuadratico(Double coeficienteQuadratico) {
        this.coeficienteQuadratico = coeficienteQuadratico;
    }

    public Double getCoeficienteLinear() {
        return coeficienteLinear;
    }

    public void setCoeficienteLinear(Double coeficienteLinear) {
        this.coeficienteLinear = coeficienteLinear;
    }

    public Double getTermoIndependente() {
        return termoIndependente;
    }

    public void setTermoIndependente(Double termoIndependente) {
        this.termoIndependente = termoIndependente;
    }
}
