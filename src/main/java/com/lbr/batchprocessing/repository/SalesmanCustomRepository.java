package com.lbr.batchprocessing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.lbr.batchprocessing.model.Salesman;

@Repository
public class SalesmanCustomRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Long countDistinctByCpf() {
		return (long) mongoTemplate.query(Salesman.class).distinct("cpf").all().size();
	}
}
