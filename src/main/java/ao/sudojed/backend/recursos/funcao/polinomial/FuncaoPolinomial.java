// Pacote, como definido em sua Funcao.java
package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.HashMap;
import java.util.List; // Necessário se você for usar List para domínio/contradomínio
import java.util.ArrayList; // Para inicializar Listas, se usadas
import java.util.Collections; // Para operações de lista, se usadas

import ao.sudojed.backend.recursos.funcao.Funcao;
import ao.sudojed.backend.recursos.funcao.Tendencia;

/**
 * Classe que representa uma função polinomial.
 * Herda de Funcao e gerencia os coeficientes do polinômio.
 */
public class FuncaoPolinomial extends Funcao {
    protected double[] coeficientes; // Coeficientes em ordem decrescente de grau

    /**
     * Construtor para FuncaoPolinomial.
     * @param coeficientes Um array de doubles representando os coeficientes
     * em ordem decrescente de grau (ex: para ax^2 + bx + c, usar {a, b, c}).
     */
    public FuncaoPolinomial(double[] coeficientes) {
        if (coeficientes == null || coeficientes.length == 0) {
            throw new IllegalArgumentException("Coeficientes não podem ser nulos ou vazios.");
        }
        this.coeficientes = coeficientes;
        // Define a lei de formação inicial baseada nos coeficientes
        setLeiFormacao(gerarLeiFormacao());
        // Propriedades iniciais (podem ser calculadas ou definidas posteriormente)
        setDominio("Todos os números reais"); // Domínio de polinômios é sempre R
        setContradominio("Depende do grau e coeficientes"); // Varia
        setParidade("Não definida ou Par/Ímpar"); // Será definida por subclasses ou cálculo
        setZeros("Ainda não calculado");
        setVariacaoSinal("Ainda não calculado");
        setContinuidade("Contínua em todo o seu domínio"); // Polinômios são contínuos
        setMonotonia(new HashMap<>());
        setConcavidade(new HashMap<>());
    }

    // --- Métodos herdados de Funcao ---

    @Override
    public void limitar(int tendencia) {
        // Implementação simplificada de limite para polinômios
        // Esta é uma simplificação. Limites para um ponto específico ou infinito
        // podem ser mais complexos de representar textualmente ou numericamente.
        if (coeficientes.length == 1) { // Função constante f(x) = c
            System.out.println("Limite da função constante é " + coeficientes[0]);
            return;
        }

        double coeficienteLider = coeficientes[0];
        int grau = coeficientes.length - 1;

        if (tendencia == 1) { // Tendendo a +infinito
            if (coeficienteLider > 0) {
                System.out.println("Limite quando x -> +infinito: +infinito");
            } else {
                System.out.println("Limite quando x -> +infinito: -infinito");
            }
        } else if (tendencia == -1) { // Tendendo a -infinito
            if (grau % 2 == 0) { // Grau par
                if (coeficienteLider > 0) {
                    System.out.println("Limite quando x -> -infinito: +infinito");
                } else {
                    System.out.println("Limite quando x -> -infinito: -infinito");
                }
            } else { // Grau ímpar
                if (coeficienteLider > 0) {
                    System.out.println("Limite quando x -> -infinito: -infinito");
                } else {
                    System.out.println("Limite quando x -> -infinito: +infinito");
                }
            }
        } else { // Tendendo a um ponto específico (para polinômios, é só calcular f(x))
            System.out.println("Limite para x = " + tendencia + ": " + calcular(tendencia));
        }
    }

    @Override
    public void derivar() {
        // Derivada de um polinômio: (ax^n)' = n*ax^(n-1)
        if (coeficientes.length == 1) { // Derivada de uma constante é 0
            this.coeficientes = new double[]{0};
        } else {
            double[] novaCoeficientes = new double[coeficientes.length - 1];
            for (int i = 0; i < novaCoeficientes.length; i++) {
                novaCoeficientes[i] = coeficientes[i] * (coeficientes.length - 1 - i);
            }
            this.coeficientes = novaCoeficientes;
        }
        setLeiFormacao(gerarLeiFormacao()); // Atualiza a lei de formação após derivar
        System.out.println("Função derivada para: " + getLeiFormacao());
        // Você precisaria recalcular outras propriedades (zeros, monotonia, etc.) aqui.
    }

    @Override
    public void integrar() {
        // Integral de um polinômio: Integral(ax^n) = (a/(n+1))x^(n+1) + C
        // Simplificação: Esta implementação não lida com a constante de integração 'C'.
        // Uma implementação mais robusta poderia retornar uma nova classe que representa
        // a família de integrais ou ter um parâmetro para C.

        double[] novaCoeficientes = new double[coeficientes.length + 1];
        for (int i = 0; i < coeficientes.length; i++) {
            int grauAtual = coeficientes.length - 1 - i;
            novaCoeficientes[i] = coeficientes[i] / (grauAtual + 1);
        }
        novaCoeficientes[coeficientes.length] = 0; // Termo constante C (aqui 0 para simplificação)
        this.coeficientes = novaCoeficientes;

        setLeiFormacao(gerarLeiFormacao()); // Atualiza a lei de formação após integrar
        System.out.println("Função integrada para: " + getLeiFormacao() + " (+ C)");
        // Você precisaria recalcular outras propriedades (zeros, monotonia, etc.) aqui.
    }

    // --- Métodos Específicos de FuncaoPolinomial ---

    /**
     * Calcula o valor da função polinomial para um dado x.
     * Este é o método fundamental de "calcular" que outras classes poderiam ter.
     */
    public double calcular(double x) {
        double resultado = 0;
        int grau = coeficientes.length - 1;
        for (int i = 0; i < coeficientes.length; i++) {
            resultado += coeficientes[i] * Math.pow(x, grau - i);
        }
        return resultado;
    }

    /**
     * Gera uma representação em String da lei de formação do polinômio.
     * Este é similar ao toString() que já tínhamos, mas agora chamado internamente.
     */
    protected String gerarLeiFormacao() {
        StringBuilder sb = new StringBuilder();
        int grau = coeficientes.length - 1;

        for (int i = 0; i < coeficientes.length; i++) {
            double coef = coeficientes[i];
            int currentGrau = grau - i;

            // Ignora termos com coeficiente zero, a menos que seja o único termo (função nula)
            if (coef == 0 && coeficientes.length > 1) {
                continue;
            }

            if (i > 0 && coef > 0) {
                sb.append(" + ");
            } else if (i > 0 && coef < 0) {
                sb.append(" - ");
                coef = Math.abs(coef);
            }

            if (currentGrau == 0) { // Termo constante
                sb.append(String.format("%.2f", coef));
            } else if (currentGrau == 1) { // Termo de 1º grau (ax)
                if (coef == 1 && i != 0) { // +x
                    sb.append("x");
                } else if (coef == -1 && i != 0) { // -x
                    sb.append("x"); // O '-' já foi adicionado
                } else if (coef == 1 && i == 0) { // x como primeiro termo
                    sb.append("x");
                } else if (coef == -1 && i == 0) { // -x como primeiro termo
                    sb.append("-x");
                }
                else {
                    sb.append(String.format("%.2f", coef)).append("x");
                }
            } else { // Termos de grau superior
                if (coef == 1 && i != 0) { // +x^n
                    sb.append("x^").append(currentGrau);
                } else if (coef == -1 && i != 0) { // -x^n
                    sb.append("x^").append(currentGrau); // O '-' já foi adicionado
                } else if (coef == 1 && i == 0) { // x^n como primeiro termo
                     sb.append("x^").append(currentGrau);
                } else if (coef == -1 && i == 0) { // -x^n como primeiro termo
                    sb.append("-x^").append(currentGrau);
                }
                else {
                    sb.append(String.format("%.2f", coef)).append("x^").append(currentGrau);
                }
            }
        }
        if (sb.length() == 0) { // Caso a função seja f(x) = 0
            return "0.00";
        }
        return sb.toString();
    }

    @Override
    public double limitar(double ponto, Tendencia tendencia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

