package com.suscriptionService.subscriptionMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suscriptionService.subscriptionMicroservice.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long>{
	
	public Subscription findOneByEmail(String subscriptionEmail);

}
