FROM java:8
EXPOSE 8090
COPY /target/api-almundo-callcenter-0.0.1-SNAPSHOT.jar api-almundo-callcenter-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-almundo-callcenter-0.0.1-SNAPSHOT.jar"]