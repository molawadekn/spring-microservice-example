# spring-microservice-example

This is a sample Java 8 / Maven / Spring Boot  application that can be used as a starter for creating a microservice.

**How to run**:
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

 - Clone this repository
 - Make sure you are using JDK 1.8 and Maven 3.x
 - You can build the project and run the tests by running mvn clean package
 - Once successfully built, you can run the service by one of these two methods:
        java -jar -Dspring.profiles.active=default target/spring-student-service-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=default"
Check the stdout or boot_example.log file to make sure no exceptions are thrown

1. **spring-student-microservice:**
  It allows registration of students who have attended seminar. It also provides offers based on location and course given.
This microservice will run on port 8080. It uses mysql db(h2 db for now)
It uses spring JPA, Spring rest.attached api json in source code.

2. **spring course-offer-microservice:**
 It stores all offers in h2 db.Offers are stored by course and state with discount. This service runs on port 9090.
 It uses H2 DB, Spring JPA, Spring rest
 attached api json in source code.
 
 ![arch](https://user-images.githubusercontent.com/80741259/115998705-28d51580-a606-11eb-9f10-84bcdffe3e6b.png)
for questions and comments contact molawadekn@gmail.com
