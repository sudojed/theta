package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coenficienteLinear;

    public Afim(String expr) {
        String regex = "(^[+-]?\\d+)x([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("2x+5");
        if (matcher.find()) {
            this.coeficienteAngular = Double.valueOf(matcher.group(1));
            this.coeficienteAngular = Double.valueOf(matcher.group(2));

        }
    }

    public Afim(Double coeficienteAngular, Double coeficienteLinear) {
        this.coeficienteAngular = coeficienteAngular;
        this.coenficienteLinear = coeficienteLinear;
    }

    

}