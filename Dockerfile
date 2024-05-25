FROM bellsoft/liberica-openjdk-debian:21

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/epicureexpressdb \
    SPRING_DATASOURCE_USERNAME=admin \
    SPRING_DATASOURCE_PASSWORD=admin

COPY target/EpicureExpress.jar /app/

EXPOSE 8080

ENTRYPOINT java -jar /app/EpicureExpress.jar
