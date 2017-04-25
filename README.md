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

#### Testing Application
Want to check if application is up? Simply paste this URL in browser.
URL - ```http://localhost:8080/coreapi/test```

Expected Output: ```Your Spring Boot Application is up! Try running other methods. Check README for more details.```

#### Methods List -
*   URL for *Getting all transactions* <br/>
Operation -  **POST** <br/>
URI - ```http://localhost:8080/coreapi/getAllTransactions```<br/>
cURL command - ```curl -X POST http://localhost:8080/coreapi/getAllTransactions```

*   URL for *Getting Average Monthly Expenses and Income* <br/>
Operation - **GET** <br/>
URI - ```http://localhost:8080/coreapi/getAverages```<br/>
cURL command - ```curl -X GET http://localhost:8080/coreapi/getAverages```

## Useful Links
1.  Spring Project Website: Use [this](https://spring.io/) link.  
i.  Consuming a RESTful Web Service: Use [this](https://spring.io/guides/gs/consuming-rest/) link.

### Notes
1.  Spring STS has been used to develop this project.
2.  This project was tested as Spring-Boot Application as well as a runnable JAR file.
3.  JUnit test classes have not been written for this project.
4.  Have shown documentation framework, Swagger Implementation in some of the model classes.
5.  Reach out to me: gisgaurav@gmail.com
