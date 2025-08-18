package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coenficienteLinear;
    private String leiFormacao;

    public Afim(String expr) {
        String regex = "((^[+-]?\\d+)x)([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        this.leiFormacao = expr;
        Matcher matcher = pattern.matcher(leiFormacao);
        if (matcher.find()) {
            this.coeficienteAngular = Double.valueOf(matcher.group(2));
            this.coenficienteLinear = Double.valueOf(matcher.group(3));
        }
    }

    public Afim(Double coeficienteAngular, Double coeficienteLinear) {
        this.coeficienteAngular = coeficienteAngular;
        this.coenficienteLinear = coeficienteLinear;
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

}