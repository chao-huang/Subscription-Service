package com.suscriptionService.eventMicroservice.web.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suscriptionService.eventMicroservice.domain.Subscription;

@RestController
public class EventController {
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendEvent")
	@ResponseBody
	public String generateEnvent(@RequestBody Subscription suscription) {
		return "success";
	}

}
