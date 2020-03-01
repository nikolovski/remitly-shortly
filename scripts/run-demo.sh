#!/bin/bash
cd ../remitly-shortly-be && mvn spring-boot:run &
cd ../remitly-shortly-ui && npm run start