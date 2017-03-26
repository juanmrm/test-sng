# test-sng
Prueba con Spring Boot, HATOAS, REST, MongoDB Embebido, Java Mail y Slack.

Se trata de una aplicacion Spring Boot que expone un API REST.
Utiliza una base de datos MongoDB embebida para almacenar los mensajes que se van gestionando.

- http://localhost:8082/api/: muestra info del api
- http://localhost:8082/api/metrics: muestra informacion sobre las metricas de la aplicacion.
- http://localhost:8082/api/message/{id}: recupera un mensaje a partir del identificador id.
- http://localhost:8082/api/mail: envia un mail si hay algun sender configurado (mail.sender in application.propeties con valor java o sendgrid)
- http://localhost:8082/api/slack: envio de mensaje con slack.

# Configuracion application.properties

JAVA MAIL:
Para probar el envio por java mail desactivar (https://www.google.com/settings/security/lesssecureapps) y configurar:
- spring.mail.username=user@gmail.com
- spring.mail.password=pass
- mail.sender=java

SLACK:
Para probar el envio de mensajes a slack hace falta indicar un token adecuado (https://api.slack.com/custom-integrations/legacy-tokens)
- slack.token=xxxx-xxxxxxxxx-xxxx 

# Prerequisitos
Maven 3.x
JDK 1.8.x

# Despliegue de 2 maneras
Dentro de la carpeta del proyecto (cd test-sng)

En WINDOWS:

	1 - Directamente
		1.1 mvnw spring-boot:run
		
	2 - Empaquetado primero 
		2.1 mvnw clean package 
		2.2 java -jar target\prueba-tecnica-0.0.1-SNAPSHOT.jar  
		
EN UNIX:

	1 - Construccion
		1.1 mvn clean install
		
	2 - Ejecucion 
		2.1 java -jar target\prueba-tecnica-0.0.1-SNAPSHOT.jar  

# Test del api
Por ejemplo con Postman, Curl...

En la carpeta src/test/resources se pueden encontrar imagenes con los ejemplos de las llamadas.

