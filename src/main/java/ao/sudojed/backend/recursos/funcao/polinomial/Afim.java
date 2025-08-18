package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coeficienteLinear;
    private String leiFormacao;

    public static void main(String[] args) {
        System.out.println(new Afim("2x-7").derivar());
    }

    public Afim(String expr) {
        String regex = "((^[+-]?\\d+)x(^1)?)([+-]?\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        this.leiFormacao = expr;
        Matcher matcher = pattern.matcher(leiFormacao);
        if (matcher.find()) {
            this.coeficienteAngular = Double.valueOf(matcher.group(2));
            this.coeficienteLinear = Double.valueOf(matcher.group(4));
        }
    }

    public Afim(Double coeficienteAngular, Double coeficienteLinear) {
        this.coeficienteAngular = coeficienteAngular;
        this.coeficienteLinear = coeficienteLinear;
    }

    @Override
    public Double derivar() {
        String regex = "((^[+-]?\\d+)x)([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);
        Double derivada = .0;
        if (matcher.find()) {
            derivada = Double.valueOf(matcher.group(2));
        }
        return derivada;
    }
    
    @Overrid
    public Double integrar(){
        
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
