# healthServices
Code Test
This is a Spring Boot Application which can be launched as a Java Application by directly running the "/HealthService/src/main/java/com/javatpoint/SpringBootStarter.java" file.

Once the project is exported and all the maven dependencies mentioned in pom.xml are built you can launch the java application using above class and hit http://localhost:8080/healthServices to view the services which are polled (/HealthService/src/main/java/com/javatpoint/controller/ApiPoller.java).

Features to add new service and delete existing services are also developed using Restfull Webservice calls.

In this project Services are being Serialized and read/written from/to a .dat file instead of which we can enhance this in future to read/write from a database.

