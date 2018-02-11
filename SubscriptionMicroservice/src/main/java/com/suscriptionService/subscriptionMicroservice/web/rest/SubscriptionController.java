package com.suscriptionService.subscriptionMicroservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suscriptionService.subscriptionMicroservice.domain.Subscription;
import com.suscriptionService.subscriptionMicroservice.repository.SubscriptionRepository;
import com.suscriptionService.subscriptionMicroservice.service.ClientEventService;
import com.suscriptionService.subscriptionMicroservice.service.ClientMailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="Subscription Controller", description="Operations which can be to manage Subscriptions")
public class SubscriptionController {

	private final Logger log = LoggerFactory.getLogger(SubscriptionController.class);
	
	@Autowired
	private ClientMailService clientMailService;
	@Autowired
	private ClientEventService clientEventService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	
	public SubscriptionController(ClientMailService clientMailService, 
			ClientEventService clientEventService) {
		this.clientMailService = clientMailService;
		this.clientEventService = clientEventService;
	}
	
	@ApiOperation(value="Let create a new Subscription", response = Long.class)
	@RequestMapping(method = RequestMethod.POST, value = "subscription")
	@ResponseBody
	public ResponseEntity<?> newSubscription(@RequestBody Subscription subscription) {
		Subscription response;
		
		//save subscription in database
		if(subscription.getEmail() != null) {
			Subscription aux = subscriptionRepository.findOneByEmail(subscription.getEmail());
			if(aux != null) {
				log.error("It already exists another subcription with the same email");
				return ResponseEntity.status(HttpStatus.FORBIDDEN).
						body("It already exists another subscription with the same Email");
			}
			
			response = subscriptionRepository.save(subscription);
		}
		else {
			log.error("The field Email is compulsory");
			return ResponseEntity.badRequest().body("The field Email is compulsory");
		}
		//Connect with MailService
		try {
			String responseMailService = clientMailService.sendMail(subscription);
			//check if the response is "success"
			if(!responseMailService.equals("success")) {
				log.error("Mail can not be sended");
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			log.info("Mail has been sended correctly");
		}
		catch(Exception e) {
			log.error("Error connecting with mail service",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		try {
			//Connect with EventService
			String responseEventService = clientEventService.sendEvent(subscription);
			//check if the response is "success"
			if(!responseEventService.equals("success")) {
				log.error("Event can not be sended");
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
		}
		catch(Exception e1) {
			log.error("Error connnection with event service",e1);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		return ResponseEntity.ok(response.getId());
	}
	
}
