FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8082

COPY ./target/UserService-0.0.1-SNAPSHOT.jar UserService.jar

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar ./UserService.jar"]