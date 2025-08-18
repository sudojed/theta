package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coeficienteLinear;
    private String leiFormacao;
    private String regex = "(^[+-]?\\d+)x(^(1))?([+-]?\\d+)?";
    private String derivada;
    private String integral;
    public static void main(String[] args) {
        System.out.println(new Afim("2x-7").derivar());
    }

    public Afim(String expr) {
        
        Pattern pattern = Pattern.compile(regex);
        this.leiFormacao = expr.replace("x", "x^1");
        Matcher matcher = pattern.matcher(leiFormacao);
        if (matcher.find()) {
            this.coeficienteAngular = Double.valueOf(matcher.group(1));
            this.coeficienteLinear = Double.valueOf(matcher.group(4));
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
            this.derivada = matcher.group(2);
        }
        return Double.parseDouble(derivada);
    }
    
    @Override
    public String integrar(){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);
        if (matcher.find()) {
            this.integral = leiFormacao.replace("^1", "^2");
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
