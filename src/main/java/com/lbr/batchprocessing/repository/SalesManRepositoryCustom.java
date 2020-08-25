package com.lbr.batchprocessing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.lbr.batchprocessing.model.SalesMan;

@Repository
public class SalesManRepositoryCustom {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Long countSalesManDistinctByCpf() {
		return (long) mongoTemplate.query(SalesMan.class).distinct("cpf").all().size();
	}
}
