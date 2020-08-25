package com.lbr.batchprocessing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Sales;
import com.lbr.batchprocessing.model.WorstSeller;
import com.lbr.batchprocessing.repository.SalesRepository;
import com.lbr.batchprocessing.repository.SalesRepositoryCustom;

@Service
public class SalesService {

	private static final Logger logger = LoggerFactory.getLogger(SalesService.class);

	@Autowired
	private SalesRepository repository;
	@Autowired
	private SalesRepositoryCustom customRepository;

	public void saveAll(List<? extends Sales> sales) {
		repository.saveAll(sales);
	}
	
	public BiggestSale findBiggestSale() {
		return customRepository.findBiggestSale();
	}
	
	public WorstSeller findWorstSeller() {
		return customRepository.findWorstSeller();
	}
}
