FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8083

COPY ./target/TweetService-0.0.1-SNAPSHOT.jar TweetService.jar

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar ./TweetService.jar"]