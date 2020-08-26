package com.lbr.batchprocessing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.lbr.batchprocessing.model.Customer;

@Repository
public class CustomerRepositoryCustom {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Long countCustomerDistinctByCnpj() {
		return (long) mongoTemplate.query(Customer.class).distinct("cnpj").all().size();
	}
}