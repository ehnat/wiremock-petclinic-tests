## Wiremock-petclinic-tests

The projects contains a few tests which use mocked api. 
Api calls  are mocked by using Wiremock.


### Technical description
- Used technologies: Groovy 3.0, Java 8, Gradle
- Used frameworks/libraries: Wiremock, RestAssured, Spock 2.0
- Test reports: Gradle

### Mocked application:
Repository for Petclinic application:
`https://github.com/spring-petclinic/spring-petclinic-rest`.

There is detailed information how to configure and run application.

Below I put a shortcut how to run Petclinic:
- `git clone https://github.com/spring-petclinic/spring-petclinic-rest.git`
- `cd spring-petclinic-rest`
- `./mvnw spring-boot:run` (in windows `mvnw spring-boot:run`)

### Wiremock
- Wiremock [documentation] (http://wiremock.org/docs/getting-started/)
- Stubs are run before every test and clean after test
- wiremock server: package `mock-server`
- stubs: are build `api-tests > main > mock > VetStubsCreator.groovy`

### How to run wiremock server
Wiremock is started by using `WiremockRunner.java` from package `mock-server`.
Server is started on 443 port and on port declared by user. When it is started from IDE http port needs to be set:

![Alt text](files/wiremock_start.PNG?raw=true "IDE configuration")

### Call stubs:
- `http://localhost:{port}/` (or `https://localhost/`) + proper url path
- check list of mocks available on server: `http(s)://localhost:{port}/__admin/mappings`

### Postman collection:
Folder `postman` - there is a postman collection which I created to support myself with manual checking api requests.

### How to run tests
1. run wiremock server (point above `How to run wiremock server`)
1. run tests:
- mac: `./gradlew clean test --info --continue`
- windows: `gradlew.bat clean test --info --continue`

### Test reports:
- Gradle: `api-tests/build/reports/tests/test/index.html`
- JUnit: `api-tests/build/reports/test-results/test/*.xml`