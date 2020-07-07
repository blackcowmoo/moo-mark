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

ENV GOOGLE_CLIENT_ID ''
ENV GOOGLE_CLIENT_SECRET ''
ENV REDIS_HOST localhost
ENV REDIS_PORT 6379
ENV REDIS_PASSWORD ''
ENV MYSQL_URL jdbc:mysql://localhost:3306/db?serverTimezone=UTC&characterEncoding=UTF-8
ENV MYSQL_USERNAME ''
ENV MYSQL_PASSWORD ''

EXPOSE 8080
STOPSIGNAL SIGINT

CMD ["java", "-jar", "moomark.jar"]
