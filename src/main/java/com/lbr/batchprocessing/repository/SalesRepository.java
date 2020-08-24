package com.lbr.batchprocessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lbr.batchprocessing.model.Sales;

public interface SalesRepository extends MongoRepository<Sales, String>{

}
