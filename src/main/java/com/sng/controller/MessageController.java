package com.sng.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sng.dto.ApiInfo;
import com.sng.dto.MailRequest;
import com.sng.dto.Message;
import com.sng.dto.Request;
import com.sng.dto.ResultInfo;
import com.sng.dto.SlackRequest;
import com.sng.metrics.MetricService;
import com.sng.repository.MessageRepository;
import com.sng.sender.JavaSender;
import com.sng.sender.MessageStatus;
import com.sng.sender.SendGridSender;
import com.sng.sender.Sender;
import com.sng.sender.SenderType;

/**
 * Definicion del controlador MessageController.
 * @author juanmiguel
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("api")
public class MessageController {

	private static final String HOST = "localhost:";
	private static final String SCHEME = "http://";
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageRepository repository;
	
	@Autowired
	private MetricService metrics;
	
	@Autowired
	@Qualifier("slacksender")
	private Sender slackSender;

	//Mail sender si esta disponible java mail o sendgrid
	private Sender mailSender; 

	private ApiInfo info;

	private String port;
	
	/**
	 * Retorna la informacion del api.
	 * @return
	 */
	@RequestMapping(value="/", method={RequestMethod.GET, RequestMethod.OPTIONS})
	public HttpEntity<ApiInfo> info() {
		return ResponseEntity.ok(this.info);
    }
	
	/**
	 * Retorna la informacion con las metricas del servicio.
	 * @return
	 */
	@RequestMapping(value="/metrics")
	@ResponseBody
	public Map<MessageStatus, Integer> metrics() {
		return this.metrics.getMetrics();
    }
	
	/**
	 * Obtiene un mensaje a partir del id.
	 * @return
	 */
	@RequestMapping(value="/message/{id}")
	public HttpEntity<Message> retrieveMessageById(@PathVariable String id) {
		Message msg = this.repository.findOne(id);
		if(msg != null){
			return ResponseEntity.ok(msg);
		}
		else return ResponseEntity.notFound().build();
    }
    
	/**
	 * Envia un mensaje por java mail.
	 * @return
	 * @throws URISyntaxException 
	 */
	@RequestMapping(value="/mail", method=RequestMethod.POST)
	public HttpEntity<Message> sendByJavaMail(@RequestBody final MailRequest mail) throws URISyntaxException {
		if(this.mailSender != null){
			ResultInfo res = this.mailSender.send(mail);
			return ResponseEntity.created(this.storeInRepositoryAndUpdateMetrics(res, mail)).build();
		}
		else return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();		
	}
	
	/**
	 * Envia un mensaje por sendGrid.
	 * @return
	 * @throws URISyntaxException 
	 */
	@RequestMapping(value="/slack", method=RequestMethod.POST)
	public HttpEntity<Message> sendBySlack(@RequestBody final SlackRequest mail) throws URISyntaxException {
		ResultInfo res = this.slackSender.send(mail);
		return ResponseEntity.created(this.storeInRepositoryAndUpdateMetrics(res, mail)).build();
	}
		
	/**
	 * Almacena en el repositorio el mensaje y actualiza las metricas.
	 * 
	 * @param res el resultado del envio.
	 * @param req la peticion de entrada.
	 * @return la URI que identifica al recurso.
	 * @throws URISyntaxException
	 */
	private URI storeInRepositoryAndUpdateMetrics (ResultInfo res, Request req) throws URISyntaxException {
		String id = UUID.randomUUID().toString();
		Message msg = new Message(id, res.getStatus(), res.getErrorMessage(), req);
		this.repository.save(msg);
		this.metrics.updateMetrics(res);
		URI location = new URI(SCHEME + HOST + port + "/api/message/" + msg.getId());
		return location;
	}

	@PostConstruct
	public void buildApiInfo(){
		this.port = env.getProperty("server.port");
		this.prepareMailSender(env.getProperty("mail.sender"));
		this.info = new ApiInfo();
		this.info.addLink(new Link(SCHEME + HOST + port + "/api", Link.REL_SELF));
		this.info.addLink(new Link(SCHEME + HOST + port + "/api/metrics", "metrics"));
		this.info.addLink(new Link(SCHEME + HOST + port + "/api/message/{id}", "message"));
		this.info.addLink(new Link(SCHEME + HOST + port + "/api/mail", "javamail"));		
		this.info.addLink(new Link(SCHEME + HOST + port + "/api/slack", "slack"));		
	}
	
	/**
	 * Prepara el sender si esta habilitado en el properties.
	 */
	private void prepareMailSender(String type){	
		if (type != null) {
			switch(SenderType.valueOf(type.toUpperCase())){
			case JAVA:
				this.mailSender = context.getBean(JavaSender.class);
				break;
			case SENDGRID:
				this.mailSender = context.getBean(SendGridSender.class);
				break;
			default:
				this.mailSender = null;
				break;
			}
		}
	}
}
