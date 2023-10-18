## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info

This is an application providing rest api for retrieving price information.

## Technologies

Project is created with:

* Java: 17
* Maven: 3.9.5
* Spring boot: 3.1.4
* H2 in memory database: 2.1.212
* Flyway: 9.16.3
* Lombok: 1.18.30
* OpenApi/Swagger (springdoc-openapi-starter-webmvc-ui): 2.2.0

## Setup

To run this project you need to install jdk 17 or above. The easiest option is to run if from IDE like intelij idea.
Other option is to run using maven, however it requires to some additional setup. Some info can be found
here: https://www.baeldung.com/spring-boot-run-maven-vs-executable-jar

Some test data are loaded automatically to db over the startup using flyway script in resources/db/migration. 
The data can be check on
http://localhost:8080/h2-console/
JDBC url: jdbc:h2:mem:testdb
user: su
password: su

As an example:
http://localhost:8080/api/prices?applicationDate=2020-06-15T00:00:00&productId=35455&brandId=1
can be run from browser or postman
More info about the endpoint can be found once the application is running on
http://localhost:8080/swagger-ui/index.html

