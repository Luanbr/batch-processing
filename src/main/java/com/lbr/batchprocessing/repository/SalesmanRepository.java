package com.lbr.batchprocessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lbr.batchprocessing.model.Salesman;

public interface SalesmanRepository extends MongoRepository<Salesman, String>{

}
