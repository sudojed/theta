package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Afim extends FuncaoPolinomial {

    private Double coeficienteAngular;
    private Double coeficienteLinear;
    private String leiFormacao;
    private String regex = "^([+-]?\\d+)x(?:\\^(\\d+))?([+-]?\\d+)?$"; // aceita expoente com mais de 1 dígito
    private String derivada;
    private String integral;

    public static void main(String[] args) {
        System.out.println(new Afim("2x-7").integrar()); // deve dar "x^2 - 7x"
    }

    public Afim(String expr) {
        this.leiFormacao = expr.contains("^") ? expr : expr.replaceAll("x(?!\\^)", "x^1");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);

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
        this.leiFormacao = coeficienteAngular + "x^1" + (coeficienteLinear >= 0 ? "+" : "") + coeficienteLinear;
    }

    @Override
    public Double derivar() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);
        if (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int n = matcher.group(2) == null ? 1 : Integer.parseInt(matcher.group(2));
            this.derivada = String.valueOf(a * n);
        }
        return Double.parseDouble(derivada);
    }

    @Override
    public String integrar() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.leiFormacao);

        if (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int n = matcher.group(2) == null ? 1 : Integer.parseInt(matcher.group(2));
            int novoGrau = n + 1;
            double novoCoef = (double) a / novoGrau;

            StringBuilder sb = new StringBuilder();
            sb.append(novoCoef).append("x^").append(novoGrau);

            if (matcher.group(3) != null) {
                int b = Integer.parseInt(matcher.group(3));
                sb.append(b >= 0 ? "+" : "").append(b).append("x");
            }

            this.integral = sb.toString();
            return this.integral;
        }
        throw new IllegalStateException("Não foi possível integrar: " + this.leiFormacao);
    }

}
