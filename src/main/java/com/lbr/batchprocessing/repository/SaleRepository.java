package com.lbr.batchprocessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lbr.batchprocessing.model.Sale;

public interface SaleRepository extends MongoRepository<Sale, String>{

}
