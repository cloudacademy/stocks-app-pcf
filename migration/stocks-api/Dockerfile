FROM amazoncorretto:25 AS builder

RUN yum install -y maven tree && yum clean all

COPY src ./src
COPY pom.xml .
RUN mvn -B clean package spring-boot:repackage
RUN tree

FROM amazoncorretto:25-alpine

RUN mkdir -p /cloudacademy/app
WORKDIR /cloudacademy/app
COPY --from=builder target/stocks-*.jar ./stocks-api.jar
RUN chown -R 1001:1001 /cloudacademy/app && chmod -R 755 /cloudacademy/app

USER 1001
EXPOSE 8080

CMD ["java", "-jar", "stocks-api.jar"]
