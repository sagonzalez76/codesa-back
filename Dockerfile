# Etapauúnica: ejecutar en modo desarrollo con autorecarga
FROM maven:3.9.6-eclipse-temurin-17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el código fuente
COPY . .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw || true

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la app en modo desarrollo
CMD ["mvn", "spring-boot:run"]
