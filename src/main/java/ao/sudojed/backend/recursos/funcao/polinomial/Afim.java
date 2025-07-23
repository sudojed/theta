package ao.sudojed.backend.recursos.funcao.polinomial;

public class Afim extends FuncaoPolinomial {
    private double a;
    private double b;

    /**
     * Construtor para FuncaoAfim.
     * @param a O coeficiente 'a' da função (ax + b).
     * @param b O coeficiente 'b' da função (ax + b).
     */
    public Afim(double a, double b) {
        // Uma função afim tem sempre 2 coeficientes: [a, b]
        super(new double[]{a, b});
        this.a = a;
        this.b = b;
        // Atualiza propriedades específicas da função afim
        setLeiFormacao(String.format("f(x) = %.2fx %s %.2f", a, (b >= 0 ? "+" : "-"), Math.abs(b)));
        setZeros(calcularZero()); // Calcula e define o zero da função afim
        setParidade("Nem par nem ímpar (a menos que b=0 e/ou a=0)"); // Funções afins não são tipicamente par/ímpar
        setMonotonia(calcularMonotonia()); // Define se é crescente ou decrescente
        // Outras propriedades podem ser calculadas se necessário
    }

    // Sobrescrevendo calcular() para clareza e semelhança com a forma ax + b
    @Override
    public double calcular(double x) {
        return a * x + b;
    }

    // --- Sobrescritas dos métodos abstratos de Funcao/FuncaoPolinomial para Afim ---

    @Override
    public void limitar(int tendencia) {
        // A lógica do limite para afim já é coberta pela FuncaoPolinomial, mas podemos ser mais específicos
        if (a == 0) { // Função constante f(x) = b
            System.out.println("Limite da função constante é " + b);
        } else if (tendencia == 1) { // x -> +infinito
            if (a > 0) System.out.println("Limite quando x -> +infinito: +infinito");
            else System.out.println("Limite quando x -> +infinito: -infinito");
        } else if (tendencia == -1) { // x -> -infinito
            if (a > 0) System.out.println("Limite quando x -> -infinito: -infinito");
            else System.out.println("Limite quando x -> -infinito: +infinito");
        } else { // x -> um ponto específico (para afim, é só calcular f(x))
            System.out.println("Limite para x = " + tendencia + ": " + calcular(tendencia));
        }
    }

    @Override
    public void derivar() {
        // A derivada de f(x) = ax + b é f'(x) = a
        super.coeficientes = new double[]{a}; // Define o novo coeficiente como 'a'
        setLeiFormacao(String.format("f'(x) = %.2f", a));
        System.out.println("Função derivada para: " + getLeiFormacao());
        // Atualizar outras propriedades da derivada
        setDominio("Todos os números reais");
        setContradominio(String.format("%.2f", a)); // Contradomínio é apenas 'a'
        setZeros(a == 0 ? "Todos os números reais" : "Nenhum"); // Se a=0, f'(x)=0, todos são zeros
        setParidade("Par"); // Funções constantes são pares
        setVariacaoSinal(a > 0 ? "Positivo em R" : (a < 0 ? "Negativo em R" : "Nulo em R"));
        setMonotonia(new HashMap<>()); // Não há variação de monotonia para função constante
        setConcavidade(new HashMap<>()); // Não há concavidade para função constante
    }

    @Override
    public void integrar() {
        // A integral de f(x) = ax + b é F(x) = (a/2)x^2 + bx + C
        super.coeficientes = new double[]{a / 2.0, b, 0.0}; // [a/2, b, C=0]
        setLeiFormacao(String.format("F(x) = %.2fx^2 %s %.2fx + C", a / 2.0, (b >= 0 ? "+" : "-"), Math.abs(b)));
        System.out.println("Função integrada para: " + getLeiFormacao());
        // Propriedades da integral (será uma função quadrática)
        setDominio("Todos os números reais");
        setContradominio("Depende dos coeficientes");
        setZeros("Ainda não calculado (da integral)");
        setParidade("Ainda não calculado (da integral)");
        setVariacaoSinal("Ainda não calculado (da integral)");
        setMonotonia(new HashMap<>());
        setConcavidade(new HashMap<>());
    }

    // --- Métodos Específicos de Afim para calcular suas propriedades ---

    /**
     * Calcula o zero (raiz) da função afim f(x) = ax + b.
     * Retorna "Nenhum" se a=0 e b!=0 (função constante não nula).
     * Retorna "Todos os números reais" se a=0 e b=0 (função nula).
     */
    private String calcularZero() {
        if (a == 0) {
            return (b == 0) ? "Todos os números reais (função nula)" : "Nenhum (função constante não nula)";
        }
        double zero = -b / a;
        return String.format("%.2f", zero);
    }

    /**
     * Calcula a monotonia da função afim (crescente, decrescente, constante).
     */
    private HashMap<String, String> calcularMonotonia() {
        HashMap<String, String> monotoniaMap = new HashMap<>();
        if (a > 0) {
            monotoniaMap.put("(-inf, +inf)", "Crescente");
        } else if (a < 0) {
            monotoniaMap.put("(-inf, +inf)", "Decrescente");
        } else { // a == 0
            monotoniaMap.put("(-inf, +inf)", "Constante");
        }
        return monotoniaMap;
    }
}