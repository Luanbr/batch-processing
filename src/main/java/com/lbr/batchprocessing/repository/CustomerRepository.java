package com.lbr.batchprocessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lbr.batchprocessing.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}
