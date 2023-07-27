FROM adoptopenjdk/openjdk8

COPY build/libs/spring-boot-0.0.1-SNAPSHOT.jar /tmp/app.jar

WORKDIR /tmp

CMD ["java", "-jar", "/tmp/app.jar"]