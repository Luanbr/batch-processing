package com.lbr.batchprocessing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.SalesMan;
import com.lbr.batchprocessing.repository.SalesManRepository;
import com.lbr.batchprocessing.repository.SalesManRepositoryCustom;

@Service
public class SalesManService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesManService.class);

	@Autowired
	private SalesManRepository repository;
	@Autowired
	private SalesManRepositoryCustom customRepository;
	
	public void saveAll(List<? extends SalesMan> sellers) {
		repository.saveAll(sellers);
	}
	
	public Long countSalesManDistinctByCnpj() {
		return customRepository.countSalesManDistinctByCpf();
	}

	public void deleteAll() {
		repository.deleteAll();
	}
}
