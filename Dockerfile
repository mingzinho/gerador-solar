# Usa uma imagem com OpenJDK 17
FROM openjdk:17-jdk-slim

# Define diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado para o container
COPY target/gerador-solar-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
