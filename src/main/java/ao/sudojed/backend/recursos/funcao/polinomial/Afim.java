package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coeficienteLinear;
    private String leiFormacao;
    private String regex = "^([+-]?\\d+)x(\\^1)?([+-]?\\d+)?$";
    private String derivada;
    private String integral;

    public static void main(String[] args) {
        System.out.println(new Afim("2x-7").integrar());
    }

    public Afim(String expr) {
        Pattern pattern = Pattern.compile(regex);
        this.leiFormacao = expr.replace("x", "x^1");
        Matcher matcher = pattern.matcher(leiFormacao);

        if (matcher.find()) {
            this.coeficienteAngular = Double.valueOf(matcher.group(1));
            String b = matcher.group(3);
            this.coeficienteLinear = (b != null) ? Double.valueOf(b) : 0.0;
        } else {
            throw new IllegalArgumentException("Expressão inválida: " + expr);
        }
    }

    public Afim(Double coeficienteAngular, Double coeficienteLinear) {
        this.coeficienteAngular = coeficienteAngular;
        this.coeficienteLinear = coeficienteLinear;
    }

    @Override
    public Double derivar() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);
        if (matcher.find()) {
            this.derivada = matcher.group(1);
        }
        return Double.parseDouble(derivada);
    }

    @Override
    public String integrar() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);
        if (matcher.find()) {
            this.integral = leiFormacao.replace(matcher.group(2), "^2");
            this.integral = leiFormacao.replace(matcher.group(3), matcher.group(3).concat("x"));
        }
        return this.integral;
    }

    public void setCoeficienteLinear(Double coeficienteLinear) {
        this.coeficienteLinear = coeficienteLinear;
    }

    public void setCoeficienteAngular(Double coeficienteAngular) {
        this.coeficienteAngular = coeficienteAngular;
    }

    public Double getCoeficienteAngular() {
        return this.coeficienteAngular;
    }

    public Double getCoeficienteLinear() {
        return this.coeficienteLinear;
    }
}
