package com.lbr.batchprocessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lbr.batchprocessing.model.SalesMan;

public interface SalesManRepository extends MongoRepository<SalesMan, String>{

}
