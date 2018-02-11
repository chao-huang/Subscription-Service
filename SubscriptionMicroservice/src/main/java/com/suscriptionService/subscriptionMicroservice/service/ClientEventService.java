package com.suscriptionService.subscriptionMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.suscriptionService.subscriptionMicroservice.domain.Subscription;

@Service
public class ClientEventService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	protected String eventServiceUrl;
	
	public ClientEventService(String eventServiceUrl) {
		this.eventServiceUrl = eventServiceUrl;
	}
	
	//Method to call to email-service
	public String sendEvent(Subscription subscription) {
		ResponseEntity<?> response = restTemplate.postForEntity(eventServiceUrl + "/sendEvent",subscription,String.class);
		return response.getBody().toString();
	}
}
