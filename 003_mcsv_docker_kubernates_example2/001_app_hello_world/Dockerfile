# Utiliza una imagen base adecuada para OpenJDK 17
FROM openjdk:17-jdk-alpine

# Define el argumento con un nombre de archivo JAR predeterminado
ARG JAR_FILE=target/hola-mundo.jar

# Copia el archivo JAR al contenedor
COPY ${JAR_FILE} app.jar

# Define el comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]

