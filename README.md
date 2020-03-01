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

## Demo
There is also a live demo available at [https://remitly-shortly.cfapps.io](https://remitly-shortly.cfapps.io)

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