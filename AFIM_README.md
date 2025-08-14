# Classe Afim - Implementação de Funções Lineares

Este documento descreve a implementação da classe `Afim` no projeto Epsilon Backend, que representa funções afins (lineares) do tipo f(x) = ax + b.

## Visão Geral

A classe `Afim` estende `FuncaoPolinomial` e implementa todas as funcionalidades necessárias para trabalhar com funções lineares, incluindo:

- Parsing de expressões matemáticas
- Cálculo de zeros, monotonia e variação de sinal
- Determinação de paridade
- Cálculo de derivadas
- Análise completa da função

## Estrutura da Hierarquia

```
Funcao (classe abstrata)
  └── FuncaoPolinomial (classe abstrata)
      └── Afim (implementação concreta)
```

## Construtores

### 1. Construtor com Expressão String
```java
Afim funcao = new Afim("2x+3");
Afim funcao2 = new Afim("-x+5");
Afim funcao3 = new Afim("x-1");
```

### 2. Construtor com Coeficientes
```java
Afim funcao = new Afim(2, 3);  // f(x) = 2x + 3
Afim funcao2 = new Afim(-1, 5); // f(x) = -x + 5
```

## Principais Funcionalidades

### Propriedades Básicas
- **Coeficiente Angular (a)**: `getCoeficienteAngular()`
- **Coeficiente Linear (b)**: `getCoeficienteLinear()`
- **Grau**: Sempre 1 para funções afins
- **Lei de Formação**: Representação string da função

### Cálculos Matemáticos
- **Calcular f(x)**: `calcular(double x)`
- **Zeros da função**: `getZeros()` - retorna List<Double>
- **Derivada**: `derivar()` - retorna função constante
- **Limites**: `limitar(double ponto, Tendencia tendencia)`

### Análise da Função
- **Monotonia**: `getMonotoniaSimples()` - crescente, decrescente ou constante
- **Paridade**: `getParidade()` - PAR, IMPAR ou NENHUMA
- **Variação de Sinal**: Análise de onde a função é positiva/negativa
- **Domínio**: Sempre ℝ (todos os reais)
- **Contradomínio**: Sempre ℝ (todos os reais)

## Regras de Paridade

- **Função Par**: a = 0 (função constante)
- **Função Ímpar**: b = 0 (f(x) = ax)
- **Nem Par nem Ímpar**: a ≠ 0 e b ≠ 0

## Regras de Monotonia

- **Crescente**: a > 0
- **Decrescente**: a < 0
- **Constante**: a = 0

## Exemplos de Uso

### Exemplo Básico
```java
// Criar função f(x) = 2x + 3
Afim funcao = new Afim("2x+3");

// Obter propriedades
System.out.println("Lei: " + funcao.getLeiFormacao());        // 2.0x+3.0
System.out.println("Zero: " + funcao.getZeros().get(0));      // -1.5
System.out.println("Monotonia: " + funcao.getMonotoniaSimples()); // crescente

// Calcular valores
double resultado = funcao.calcular(2);  // 7.0
```

### Exemplo de Análise Completa
```java
Afim funcao = new Afim("3x-6");
Map<String, Object> estudo = funcao.getEstudoCompleto();

// O mapa contém:
// - leiFormacao: "3.0x-6.0"
// - grau: 1
// - dominio: "R"
// - zeros: [2.0]
// - coeficientes: {a=3.0, b=-6.0}
// - monotonia: intervalos com descrição
// - variacaoSinal: intervalos com sinal
// - paridade: NENHUMA
```

### Exemplo de Derivada
```java
Afim funcao = new Afim("5x+2");
Afim derivada = (Afim) funcao.derivar();

System.out.println("f(x) = " + funcao.getLeiFormacao());     // 5.0x+2.0
System.out.println("f'(x) = " + derivada.getLeiFormacao()); // 0.0x+5.0
```

## Formatos de Expressão Suportados

A classe aceita diversos formatos de entrada:
- `"2x+3"` - formato padrão
- `"x+1"` - coeficiente angular implícito (1)
- `"-x+2"` - coeficiente angular implícito (-1)
- `"5x-3"` - com termo negativo
- `"3x"` - sem termo linear (b=0)

## Validação de Entrada

- Expressões inválidas lançam `IllegalArgumentException`
- Apenas a variável 'x' é aceita
- Coeficientes podem ser inteiros ou decimais

## Casos Especiais

### Função Constante
```java
Afim constante = new Afim(0, 5); // f(x) = 5
// - Monotonia: constante
// - Paridade: PAR
// - Sem zeros (exceto se b=0)
```

### Função Ímpar
```java
Afim impar = new Afim(3, 0); // f(x) = 3x
// - Paridade: IMPAR
// - Zero em x = 0
// - Passa pela origem
```

## Testes

A implementação inclui testes abrangentes em `AfimTest.java` que cobrem:
- Construtores
- Cálculos matemáticos
- Análise de propriedades
- Casos especiais
- Validação de entrada

Para executar os testes:
```bash
mvn test -Dtest=AfimTest
```

## Demonstração

Execute a classe `AfimDemo` para ver exemplos práticos:
```bash
java -cp "target/classes" ao.sudojed.backend.recursos.funcao.polinomial.AfimDemo
```

## Limitações Atuais

- **Integração**: Não implementada (requer classe Quadratica)
- **Formatação**: Lei de formação usa formato decimal (ex: "2.0x+3.0")

## Arquitetura do Projeto

A implementação segue os padrões estabelecidos no projeto:
- Herança de `FuncaoPolinomial`
- Uso de enums para paridade e tendência
- Estrutura de intervalos para análise
- Mapa de propriedades para estudo completo

## Próximos Passos

1. Implementar integração quando `Quadratica` estiver disponível
2. Melhorar formatação da lei de formação
3. Adicionar suporte a expressões mais complexas
4. Implementar plotagem gráfica