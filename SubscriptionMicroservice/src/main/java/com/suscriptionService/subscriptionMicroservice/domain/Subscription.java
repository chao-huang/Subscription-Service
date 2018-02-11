package com.suscriptionService.subscriptionMicroservice.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.suscriptionService.subscriptionMicroservice.domain.enumeration.Gender;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Subscription {
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
	private Long id;
	
	@ApiModelProperty(notes= "Email direction", required = true)
	@NotNull
	private String email;
	
	@ApiModelProperty(notes= "Fist name")
	private String firstName;
	
	@ApiModelProperty(notes= "Gender")
	private Gender gender;
	
	@ApiModelProperty(notes= "Date of Bith, in milliseconds", required = true)
	@NotNull
	private Date dateOfBith;
	
	@ApiModelProperty(notes= "Flag used for acept the terms of use", required = true)
	@NotNull
	private boolean consentFlag;
	
	//It would be a foreing key of NewSletter entity
	@ApiModelProperty(notes= "Id of the newSeltter where they want to be subscripbed", required = true)
	@NotNull
	private Long newSletterId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBith() {
		return dateOfBith;
	}

	public void setDateOfBith(Date dateOfBith) {
		this.dateOfBith = dateOfBith;
	}

	public boolean isConsentFlag() {
		return consentFlag;
	}

	public void setConsentFlag(boolean consentFlag) {
		this.consentFlag = consentFlag;
	}

	public Long getNewSletterId() {
		return newSletterId;
	}

	public void setNewSletterId(Long newSletterId) {
		this.newSletterId = newSletterId;
	}
	
}
