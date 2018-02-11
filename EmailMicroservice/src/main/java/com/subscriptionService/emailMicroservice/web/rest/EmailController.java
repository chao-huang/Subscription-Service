package com.subscriptionService.emailMicroservice.web.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.subscriptionService.emailMicroservice.domain.Subscription;

@RestController
public class EmailController {
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMail")
	@ResponseBody
	public String sendMail(@RequestBody Subscription subscription) {
		return "success";
	}
}
