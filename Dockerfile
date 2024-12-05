# Etapa de construcción
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar el archivo de configuración de Maven
COPY pom.xml .

# Descargar dependencias para aprovechar el caché en caso de cambios en el código
RUN mvn dependency:resolve -DskipTests

# Copiar el código fuente del proyecto
COPY src ./src

# Construir el proyecto (sin ejecutar pruebas para acelerar el proceso)
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=builder /app/target/project-estramipyme-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
