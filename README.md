# batch-processing
* Required: JDK 11 <br />
* Required: MongoDB

### MongoDB
Configure MongoDB connection in src\main\resources.

```sh
spring:
  data:
    mongodb:
      database: batch_db
      port: 27017
      host: localhost
      #username: 
      #password:
```
### Build Application
Linux
```sh
./mvnw clean package -DskipTests
```
Windows
```sh
mvnw.cmd clean package -DskipTests
```
### Run Application

```sh
cd target
```
```sh
java -jar .\batch-processing-1.0.0.jar
```