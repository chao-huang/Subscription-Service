package com.suscriptionService.subscriptionMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.suscriptionService.subscriptionMicroservice.service.ClientEventService;
import com.suscriptionService.subscriptionMicroservice.service.ClientMailService;
import com.suscriptionService.subscriptionMicroservice.web.rest.SubscriptionController;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
				value ={ClientEventService.class, ClientMailService.class}))
//@ComponentScan(useDefaultFilters=false)
public class SubscriptionMicroserviceApplication {
	
	public static final String MAILSERVICE_URL = "http://MAIL-SERVICE";
	public static final String EVENTSERVICE_ULR = "http://EVENT-SERVICE";

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionMicroserviceApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ClientEventService eventService() {
		return new ClientEventService(EVENTSERVICE_ULR);
	}
	
	@Bean
	public ClientMailService mailService() {
		return new ClientMailService(MAILSERVICE_URL);
	}
	
	@Bean
	public SubscriptionController subscriptionController() {
		return new SubscriptionController(mailService(),eventService());
	}
}
