# batch-processing
* Required: JDK 11 <br />
* Required: Docker

### MongoDB on Docker
Before testing or running the application. Make sure to run the following command and that mongoDB has been started.

```sh
docker-compose up
```
### Run Tests
Linux
```sh
./mvnw test
```
Windows
```sh
mvnw.cmd test
```
### Build and Run Application
Linux
```sh
./mvnw clean package -DskipTests && cd target && java -jar ./batch-processing-1.0.0.jar
```
Windows
```sh
mvnw.cmd clean package -DskipTests && cd target && java -jar .\batch-processing-1.0.0.jar
```