package ao.sudojed.backend.resources.funcao.polinomial;

import ao.sudojed.backend.resources.funcao.Funcao;
import ao.sudojed.backend.resources.funcao.enums.Tendencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Afim implements Funcao {

    private Double coeficienteLinear;
    private Double coeficienteAngular;

    public Afim(Double coeficienteAngular, Double coeficienteLinear) {
        this.coeficienteAngular = coeficienteAngular;
        this.coeficienteLinear = coeficienteLinear;
    }

    @Override
    public double calcular(double x) {
        // f(x) = ax + b
        return coeficienteAngular * x + coeficienteLinear;
    }

    @Override
    public double limitar(double ponto, Tendencia tendencia) {
        // Para função afim, o limite é sempre o valor da função no ponto
        return calcular(ponto);
    }

    @Override
    public Funcao derivar() {
        // A derivada de f(x) = ax + b é f'(x) = a (constante)
        return new Afim(0.0, coeficienteAngular);
    }

    @Override
    public Funcao integrar() {
        // A integral de f(x) = ax + b é F(x) = (a/2)x² + bx + C
        // Assumindo C = 0 para a primitiva
        return new Quadratica(coeficienteAngular / 2, coeficienteLinear, 0.0);
    }

    // Getters e Setters
    public Double getCoeficienteLinear() {
        return coeficienteLinear;
    }

    public void setCoeficienteLinear(Double coeficienteLinear) {
        this.coeficienteLinear = coeficienteLinear;
    }

    public Double getCoeficienteAngular() {
        return coeficienteAngular;
    }

    public void setCoeficienteAngular(Double coeficienteAngular) {
        this.coeficienteAngular = coeficienteAngular;
    }
}
