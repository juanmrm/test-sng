# test-sng
Prueba con Spring Boot, HATOAS. REST, MongoDB Embebido, Java Mail, Slack

# Prerequisitos
Maven 3.X.X
JDK 1.8.X

# Despliegue de 2 maneras
Dentro de la carpeta del proyecto (cd prueba-tecnica)

	1 - Directamente
		1.1 mvnw spring-boot:run
		
	2 - Empaquetado primero 
		2.1 mvnw clean package 
		2.2 java -jar target\prueba-tecnica-0.0.1-SNAPSHOT.jar  
		

# Test del api
Por ejemplo con Postman, Curl...

En la carpeta src/test/resources se pueden encontrar ejemplos de las llamadas.

