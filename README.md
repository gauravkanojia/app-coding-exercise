# Project app-coding-exercise
This repository will be used to track and store code base for app-coding-exercise.<br/>
This is a Maven-based Spring Boot application, which is used for consuming an API. The docs for the API being consumed are present at [this](https://doc.level-labs.com/) link.

## Build Process
As this is a Maven-based project, the dependencies and build properties are mentioned in the parent pom.xml file. To build the project, simply run the below command in the parent directory where pom.xml is present.

```script
mvn -U clean install
```

Above command will build the project for the mentioned dependencies and will package it into a WAR file which can be deployed on a web server, such as Apache Tomcat.



## Useful Links
1.  Apache Tomcat Server Website: Use [this](http://tomcat.apache.org/) link.
2.  Spring Project Website: Use [this](https://spring.io/) link.  
i.  Consuming a RESTful Web Service: Use [this](https://spring.io/guides/gs/consuming-rest/) link.
