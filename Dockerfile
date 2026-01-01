FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY target/phm-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-XX:+UseSerialGC", "-jar", "app.jar"]
