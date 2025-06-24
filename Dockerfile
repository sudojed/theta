# Etapa 1: Construir a aplicação
FROM maven:3.9-eclipse-temurin-21 AS builder

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o projeto (pom.xml + código-fonte)
COPY pom.xml .
COPY src ./src

# Baixar dependências e compilar o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final usando JDK leve
FROM eclipse-temurin:21-jre

# Diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expor a porta (opcional: use a porta que sua app usa)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
