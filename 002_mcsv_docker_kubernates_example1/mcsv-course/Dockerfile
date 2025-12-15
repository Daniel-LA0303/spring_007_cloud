ARG MCSV_NAME=mcsv-course
FROM openjdk:17-slim AS builder

ARG MCSV_NAME

WORKDIR /app/$MCSV_NAME

# Copia el pom.xml para descargar las dependencias
COPY ./pom.xml /app

# Copia el resto del proyecto
COPY ./$MCSV_NAME/.mvn ./.mvn
COPY ./$MCSV_NAME/pom.xml . 
COPY ./$MCSV_NAME/src ./src

# Instala Maven
RUN apt-get update && apt-get install -y maven

# Ejecuta Maven para construir el proyecto y omitir las pruebas
RUN mvn clean package -Dmaven.test.skip=true

# ------------------ Segunda etapa ------------------
# Creamos una nueva imagen a partir de la etapa anterior
FROM openjdk:17-slim

WORKDIR /app

# Creamos un directorio para guardar los logs de Spring Boot
RUN mkdir ./logs

ARG MCSV_NAME
ARG TARGET_FOLDER=/app/$MCSV_NAME/target

# Copia el archivo JAR construido de la etapa de construcción
COPY --from=builder /app/$MCSV_NAME/target/*.jar mcsv-course.jar

ARG PORT_APP=8082
ENV PORT $PORT_APP

# Exponer el puerto de la aplicación
EXPOSE $PORT

# Ejecutar el archivo JAR con el puerto configurado
CMD ["java", "-jar", "mcsv-course.jar"]

