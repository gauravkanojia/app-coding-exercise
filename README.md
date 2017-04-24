# Project app-coding-exercise
This repository will be used to track and store code base for app-coding-exercise.<br/>
This is a Maven-based Spring Boot application, which is used for consuming an API. The docs for the API being consumed are present at [this](https://doc.level-labs.com/) link.

## Build Process
As this is a Maven-based project, the dependencies and build properties are mentioned in the parent `pom.xml` file. To build the project, simply run the below command in the parent directory where `pom.xml` is present.

```script
mvn -U clean install
```

Above command will build the project for the mentioned dependencies and will package it into a JAR file which can be run as any other Java JAR file.


## Running the project
Since this is a spring-boot project, it can be ran in two ways.<br/>
1.  To run this project utilizing Spring-Boot feature, simply go to the project directory and run the below command
```script
mvn spring-boot:run
```
2.  To run this project utilizing the Java JAR file, simply go to the project directory and run the below command from project directory
```script
java -jar target/app-coding-exercise-1.0.0-SNAPSHOT.jar
```

## Useful Links
1.  Spring Project Website: Use [this](https://spring.io/) link.  
i.  Consuming a RESTful Web Service: Use [this](https://spring.io/guides/gs/consuming-rest/) link.

### Notes
1.  Spring STS has been used to develop this project.
2.  This project was tested as Spring-Boot Application as well as a runnable JAR file.
