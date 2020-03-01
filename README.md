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

## Design Decisions
