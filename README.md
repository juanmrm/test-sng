# test-sng
Prueba con Spring Boot, HATOAS, REST, MongoDB Embebido, Java Mail y Slack.

Se trata de una aplicacion Spring Boot que expone un API REST.
Utiliza una base de datos MongoDB embebida para almacenar los mensajes que se van gestionando.

{
  "links": [
    {
      "rel": "self",
      "href": "http://localhost:8082/api"
    },
    {
      "rel": "metrics",
      "href": "http://localhost:8082/api/metrics"
    },
    {
      "rel": "message",
      "href": "http://localhost:8082/api/message/{id}"
    },
    {
      "rel": "javamail",
      "href": "http://localhost:8082/api/javamail"
    },
    {
      "rel": "sendgrid",
      "href": "http://localhost:8082/api/sendgrid"
    },
    {
      "rel": "slack",
      "href": "http://localhost:8082/api/slack"
    }
  ]
}

http://localhost:8082/api/metrics: muestra informacion sobre las metricas de la aplicacion.
http://localhost:8082/api/message/{id}: recupera un mensaje a partir del identificador id.
http://localhost:8082/api/javamail: envia un mensaje con java mail.
http://localhost:8082/api/sendgrid: NOT YET IMPLEMENTED
http://localhost:8082/api/slack: envio de mensaje con slack.

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

En la carpeta src/test/resources se pueden encontrar imagenes con los ejemplos de las llamadas.

