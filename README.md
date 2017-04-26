# Project app-coding-exercise
This repository will be used to track and store code base for app-coding-exercise.<br/>
This is a Maven-based Spring Boot application, which is used for consuming an API. The docs for the API being consumed are present at [this](https://doc.level-labs.com/) link.

## Pre-requisites
1.  The environment where this project will be run should have Java 8 setup with environment variables.
2.  The environment should have Maven build tool configured for building the project and running the project as `Spring-Boot` application. This step is important because unless the code is built, the corresponding JAR file will not be present and the application won't run as a Java JAR or as a `Spring-Boot` application.

## Getting the code

To get the code on your local machine, you can either fork the repository and then clone it, or you can clone this repository using the below command.

```script
git clone https://github.com/gauravkanojia/app-coding-exercise.git
```

Changes can be pushed through a pull request and will be reviewed before merging them to the main (master) repository.

For `Forking` this repository, simply click on the Fork button on the top right corner of this page.


## Build Process
As this is a Maven-based project, the dependencies and build properties are mentioned in the parent `pom.xml` file. To build the project, simply run the below command in the parent directory where `pom.xml` is present.

```script
mvn -U clean install
```

Above command will build the project for the mentioned dependencies and will package it into a JAR file which can be run as any other Java JAR file.


## Running the application

#### Steps for running the project
Since this is a `Spring-Boot` project, it can be run in two ways -<br/>
1.  To run this project utilizing `Spring-Boot` feature, simply go to the project directory and run the below command
```script
mvn spring-boot:run
```
2.  To run this project utilizing the Java JAR file, simply go to the project directory and run the below command from project directory
```script
java -jar target/app-coding-exercise-1.0.1-SNAPSHOT.jar
```

#### Testing Application
Want to check if application is up? Simply paste this URL in browser.
URL - ```http://localhost:8080/coreapi/test```

Expected Output: ```Your Spring Boot Application is up! Try running other methods. Check README for more details.```

#### Methods List -
*   URL for *Getting all transactions* <br/>
Operation -  **GET** <br/>
URI - ```http://localhost:8080/coreapi/getAllTransactions```<br/>
cURL command - ```curl -X GET http://localhost:8080/coreapi/getAllTransactions```

*   URL for *Getting Average Monthly Expenses and Income* <br/>
Operation - **GET** <br/>
URI - ```http://localhost:8080/coreapi/getAverages```<br/>
cURL command - ```curl -X GET http://localhost:8080/coreapi/getAverages```

*   URL for *Ignore Donuts from All Transactions* <br/>
Operation - **GET** <br/>
URI - ```http://localhost:8080/coreapi/ignoreDonuts```<br/>
cURL command - ```curl -X GET http://localhost:8080/coreapi/ignoreDonuts```

## Versions
The versions mentioned below are oldest to latest. <br/>
*   1.0.0-SNAPSHOT: Release with `getAllTransactions` and `getAverages` methods.
*   1.0.1-SNAPSHOT: Release with `ignoreDonuts` methods.

## Useful Links
*   Spring Project Website: Use [this](https://spring.io/) link.  
*   Consuming a RESTful Web Service: Use [this](https://spring.io/guides/gs/consuming-rest/) link.

## Notes
1.  Spring STS has been used to develop this project.
2.  This project was tested as Spring-Boot Application as well as a runnable JAR file.
3.  As the backend API is not consumable through a VPN, please make sure you're not on any VPN while running the application.
3.  JUnit test classes have not been written for this project.
4.  Have shown documentation framework, Swagger Implementation in some of the model classes.
5.  Reach out to me: gisgaurav@gmail.com
