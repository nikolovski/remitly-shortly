ECHO Starting the backend
start "Remitly Shortly BE" /D "../remitly-shortly-be" mvn spring-boot:run
ECHO Starting the frontend
start "Remitly Shortly FE" /D "../remitly-shortly-ui" npm run start
