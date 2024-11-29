# Spring Boot Project with JPA, Flyway, and REST Endpoints

This project is a Spring Boot application that demonstrates:

* The use of JPA for database interactions along with Flyway for database migrations.
* Exposes multiple RESTful endpoints for CRUD and other operations.

## Technologies Used

* Java: Development language
* Spring Boot: Application framework
* JPA (Java Persistence API): Database ORM
* Flyway: Database migration tool
* Maven: Build and dependency management

*This project was created using VsCode:* 
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)
- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- 

**Note: Project Created in Windows Machine**

# Setup Instructions for Run the project locally

1. Clone the repository: https://github.com/ycuervob/FranquiciasSpringAPI.git
2. Configure the MySql Database conection in _src\main\resources\application.properties_.

```
spring.datasource.url=jdbc:mysql://your-database-url/franquicias_db
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true
```

3. Once you have configured the Database then excecute in the main path of the project the next maven commands `.\mvnw.cmd install -f "pom.xml"`.

4. Then you can run the project with: `.\mvnw.cmd  spring-boot:run`.

5. You can consume in PORT 8080 the app with the next routes in folders _src\main\java\com\franquicias\nequi\controller_.

**NOTE: rememeber using java 17 for run the application.**

# How to deploy

* Once you have excecuted `.\mvnw.cmd install -f "pom.xml"` then would obtain a *.war* file than can be deployed in Tomcat or Jetty servers or in Docker Images created for that purpose.