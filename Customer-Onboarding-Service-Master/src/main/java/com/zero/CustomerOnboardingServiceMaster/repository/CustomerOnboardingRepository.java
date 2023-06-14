package com.zero.CustomerOnboardingServiceMaster.repository;

import com.zero.CustomerOnboardingServiceMaster.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOnboardingRepository extends MongoRepository<Customer,String> {
}
