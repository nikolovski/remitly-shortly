# Remitly Shortly
An URL shortening service

## Getting started

### Running locally
#### Requirements
1. [JRE 8](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) or [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
2. [NodeJS](https://nodejs.org/dist/v12.16.1/node-v12.16.1-x64.msi)

### Set the test db password
Set the test db password same as the user [test] in the `remitly-shortly-api/src/main/resources/application.yml`
### Run `npm install`
Navigate to `remitly-shortly-ui` and run
```bash
npm install
```
#### Run the scripts
Run `scripts/run-demo.bat` or `scripts/run-demo.sh` depending on your OS
```bash
sh scripts/run
```
#### Open `localhost:4200`
Open `localhost:4200` and the application should load

## Application Design Decisions

### Caching
The application is using the Spring Boot simple caching for simplicity and it caches only the endpoint for creating new short url. The reason for not using cache fetching the original url is that it might have been expired in the db before it expires in the cache. There is a configuration in the application where the schedule of the cache eviction can be scheduled:
```yaml
remitly:
  shortly:
    cache-eviction-cron: 0 0 0/2 * * ? #every 2 hours
```
One way to improve this caching mechanism is having a distributed cache for having multiple instances of the application which will share the same cache, such as Redis.

### Db cleanup of expired urls
Short url is saved in the database until it's expiration date determined by the below property:
```yaml
remitly:
  shortly:
    expiration-days: 14
```
Upon creation, the short url expiration date is set to now + expiration-days property. The database is queried only for for non expired urls so in order to removed the expired ones, there is a schedule for deletion of all expired configured in the following property:
```yaml
remitly:
  shortly:
    db-cleanup-cron: 0 0 12 * * ? # every day at 12pm
```
### Url validation
The url to be shortened is validated using the custom `@Url` annotation which internally uses apache commons `UrlValidator`.

### Exception handling
All REST exception handling is places in the `ShortlyExceptionHandler` REST controller advice that uses AOP to handle Runtime exeptions thrown exceptions by the service methods.

### Persistence
The default database used is MongoDB. All configuration is found in the `application.yml` and the repository beans under `com.remitly.shortly.repository.mongo`. You can notice that there is an interface `com.remitly.shortly.repository.ShortlyUrlRepository` which allows abstraction for whichever db we choose to use. I have created implementation for in-memory db for testing but becase of simplicity and time constraints it is WIP. The application will pick the correct bean according to the specified `@Profile`.

### UI
Angular frontend that utilizes PWA(offline usage) and uses cookies to store history of shortened urls in the browser. There is ability to copy the shortened url or directly navigate by clicking on it.
When shortening an url if there is any error thrown by the backend, it shows that error in a snackbar on the bottom of the page. The app redirects back to the main page if the short url it is expired or doesn't exist and displays an error.

### Improvment possibilities
1. Externalize the application configuration in another github repository and apply `@RefreshScope` to the `ConfigurationProperties` to change it without restarting the backend.
2. Have distributed caching when having multiple instances running (scaled horizontally)

## Demo
There is also a live demo available at [https://remitly-shortly.cfapps.io](https://remitly-shortly.cfapps.io).
