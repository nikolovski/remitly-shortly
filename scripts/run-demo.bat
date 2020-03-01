ECHO Starting the backend
export dbPassword=test
start "Remitly Shortly BE" /D "../remitly-shortly-be" mvn spring-boot:run
ECHO Starting the frontend
start "Remitly Shortly FE" /D "../remitly-shortly-ui" ng serve
