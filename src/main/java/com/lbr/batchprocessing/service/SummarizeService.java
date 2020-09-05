package com.lbr.batchprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.model.WorstSalesman;

@Service
public class SummarizeService {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private SaleService salesService;
	@Autowired
	private SalesmanService salesmanService;

	public Summarize create() {
		Long customersQuantity = customerService.countDistinctByCnpj();
		Long salespeopleQuantity = salesmanService.countDistinctByCnpj();
		BiggestSale biggestSale = salesService.findBiggestSale();
		WorstSalesman worstSeller = salesService.findWorstSeller();
		Summarize summarize = new Summarize(customersQuantity, salespeopleQuantity, biggestSale, worstSeller);
		return !summarize.isEmpty() ? summarize : null;
	}

	public void clean() {
		customerService.deleteAll();
		salesmanService.deleteAll();
		salesService.deleteAll();
	}
}
