FROM maven:3.6.3-openjdk-17 as maven

WORKDIR /jtracker

COPY . /jtracker

RUN mvn install

FROM openjdk:17.0.2-jdk

WORKDIR /jtracker

COPY --from=maven /jtracker/target/tracker.jar jtracker.jar

CMD ["java", "-jar", "jtracker.jar"]