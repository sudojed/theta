package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coeficienteLinear;
    private String leiFormacao;
    private String regex = "^([+-]?\\d+)x(\\^(\\d))?([+-]?\\d+)?$";
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
            String b = matcher.group(4);
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
        Double novoGrau = Double.parseDouble(matcher.group(3))+1;
        if (matcher.find()) {
            this.integral = leiFormacao.replace(matcher.group(3), Double.toString(novoGrau));
            this.integral = leiFormacao.replace(matcher.group(4), matcher.group(4).concat("x"));
        }
        return this.integral;
    }

    public void setCoeficienteLinear(Double coeficienteLinear) {
        this.coeficienteLinear = coeficienteLinear;
    }

    public void setCoeficienteAngula(Double coeficienteAngular) {
        this.coeficienteAngular = coeficienteAngular;
    }

    public Double getCoeficienteAngular() {
        return this.coeficienteAngular;
    }

    public Double getCoeficienteLinear() {
        return this.coeficienteLinear;
    }
}
