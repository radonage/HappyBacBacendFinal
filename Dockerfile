# Étape 1 : build du projet
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copier le pom et télécharger les dépendances (cache Docker optimisé)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source
COPY src ./src

# Build du jar (skip tests pour prod rapide)
RUN mvn clean package -DskipTests

# Étape 2 : image légère pour exécution
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copier le jar généré
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]