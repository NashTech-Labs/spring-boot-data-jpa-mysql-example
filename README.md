
# README.md
## Spring Boot JPA MySQL - Building Rest CRUD API example

Clone the repo::
````
git@github.com:knoldus/Spring-Boot-JPA-MySQL-Rest-CRUD-api.git
````
> ## Technology

* Java 8
* Spring Boot 2.2.1 (with Spring Web MVC, Spring Data JPA)
* PostgreSQL/MySQL
* Maven 3.6.1

## Create & Setup Spring Boot project
>Use Spring web tool or your development tool (Spring Tool Suite, Eclipse, Intellij) to create a Spring Boot project.

#### Then open pom.xml and add these dependencies:
````
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
````
### We also need to add one more dependency.
– If you want to use MySQL:
````
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
````
## Configure Spring Datasource, JPA, Hibernate
> Under src/main/resources folder, open application.properties and write these lines.

– For MySQL:

>server.port=8083
>spring.datasource.url= jdbc:mysql://localhost:3306/<DATABASE_NAME>?useSSL=false
>spring.datasource.username= <USERNAME>
>spring.datasource.password= <PASSWORD>
>spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
>spring.jpa.hibernate.ddl-auto= update

### Hibernate ddl auto (create, create-drop, validate, update)
* spring.jpa.hibernate.ddl-auto= update
### Run Spring Boot application

```
mvn spring-boot:run
```

