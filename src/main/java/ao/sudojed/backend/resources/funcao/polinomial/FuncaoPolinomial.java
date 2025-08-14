package ao.sudojed.backend.resources.funcao.polinomial;

import ao.sudojed.backend.resources.funcao.Funcao;
import ao.sudojed.backend.resources.funcao.Intervalo;
import java.util.List;

public abstract class FuncaoPolinomial implements Funcao {

    protected int grau;

    public FuncaoPolinomial() {
        this.grau = 0;
    }

    public FuncaoPolinomial(int grau) {
        this.grau = grau;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    @Override
    public List<Intervalo> getDominio() {
        return List.of(new Intervalo("-∞", "+∞"));
    }

    @Override
    public String getContinuidade() {
        return "Contínua em todo ℝ";
    }

    protected String formatarTermo(
        double coeficiente,
        String variavel,
        boolean isFirst
    ) {
        StringBuilder sb = new StringBuilder();

        if (coeficiente == 0) {
            return "";
        }

        if (!isFirst) {
            if (coeficiente > 0) {
                sb.append(" + ");
            } else {
                sb.append(" - ");
                coeficiente = Math.abs(coeficiente);
            }
        } else if (coeficiente < 0) {
            sb.append("-");
            coeficiente = Math.abs(coeficiente);
        }

        if (variavel.isEmpty()) {
            sb.append(coeficiente);
        } else {
            if (coeficiente == 1.0) {
                sb.append(variavel);
            } else {
                sb.append(coeficiente).append(variavel);
            }
        }

        return sb.toString();
    }

    protected boolean isZero(double valor, double tolerancia) {
        return Math.abs(valor) < tolerancia;
    }

    protected boolean isZero(double valor) {
        return isZero(valor, 1e-10);
    }
}
