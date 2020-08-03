# Build spring
FROM openjdk:11 as builder

COPY . /spring
WORKDIR /spring
RUN ./gradlew build -x test -x testClasses -x checkstyleMain -x checkstyleTest

# RUN Spring
FROM openjdk:11-jre-slim

COPY --from=builder /spring/build/libs/*.jar /spring/
WORKDIR /spring
RUN mv /spring/*.jar /spring/moomark.jar
RUN ls -al /spring

EXPOSE 8080
STOPSIGNAL SIGINT

CMD ["java", "-jar", "moomark.jar"]
