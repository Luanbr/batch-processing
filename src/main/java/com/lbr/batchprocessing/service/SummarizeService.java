package com.lbr.batchprocessing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.model.WorstSalesman;

@Service
public class SummarizeService {
	private static final Logger logger = LoggerFactory.getLogger(SummarizeService.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SaleService salesService;
	@Autowired
	private SalesmanService salesmanService;

	public Summarize create() {
		try {
			Long customersQuantity = customerService.countDistinctByCnpj();
			Long salespeopleQuantity = salesmanService.countDistinctByCnpj();
			BiggestSale biggestSale = salesService.findBiggestSale();
			WorstSalesman worstSeller = salesService.findWorstSeller();
			Summarize summarize = new Summarize(customersQuantity, salespeopleQuantity, biggestSale, worstSeller);
			return !summarize.isEmpty() ? summarize : null;			
		} catch (Exception e) {
			logger.error("Error while creating summary ", e);
			throw e;
		}
	}

	public void clean() {
		try {
			customerService.deleteAll();
			salesmanService.deleteAll();
			salesService.deleteAll();
		} catch (Exception e) {
			logger.error("Error during cleaning MongoDB", e);
			throw e;
		}
	}
}
