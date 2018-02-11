package com.suscriptionService.subscriptionMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.suscriptionService.subscriptionMicroservice.domain.Subscription;

@Service
public class ClientMailService {

	public static final String MAILSERVICE_URL = "http://MAIL-SERVICE";

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	protected String mailServiceUrl;
	
	public ClientMailService(String mailServiceUrl) {
		this.mailServiceUrl = mailServiceUrl;
	}
	
	//Method to call to email-service
	public String sendMail(Subscription subscription) {
		ResponseEntity<?> response = restTemplate.postForEntity(mailServiceUrl + "/sendMail",subscription,String.class);
		return response.getBody().toString();
	}
}
