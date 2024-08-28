# Usar a imagem base Maven com OpenJDK
FROM maven:3.9.9-eclipse-temurin-17-alpine

# Definir o mantenedor da imagem
LABEL maintainer="Ismael Nunes <ismaeldenunes@gmail.com>"

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar os arquivos do projeto para o diretório de trabalho
COPY . .

# Rodar o comando Maven para construir a aplicação
RUN mvn package -DskipTests

# Definir variáveis de ambiente

# Expor a porta que a aplicação vai usar
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "target/ismael-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
