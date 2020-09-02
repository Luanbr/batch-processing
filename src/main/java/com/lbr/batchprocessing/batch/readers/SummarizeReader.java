package com.lbr.batchprocessing.batch.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.model.WorstSalesman;
import com.lbr.batchprocessing.service.CustomerService;
import com.lbr.batchprocessing.service.SalesmanService;
import com.lbr.batchprocessing.service.SaleService;

@Component
public class SummarizeReader implements ItemReader<Summarize> {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private SaleService salesService;
	@Autowired
	private SalesmanService salesManService;

	@Override
	public Summarize read() {
		Summarize summarize = create();
		clean();
		return !summarize.isEmpty() ? summarize : null;
	}

	public Summarize create() {
		Long customersQuantity = customerService.countCustomerDistinctByCnpj();
		Long sellersQuantity = salesManService.countSalesManDistinctByCnpj();
		BiggestSale biggestSale = salesService.findBiggestSale();
		WorstSalesman worstSeller = salesService.findWorstSeller();
		return new Summarize(customersQuantity, sellersQuantity, biggestSale, worstSeller);
	}

	public void clean() {
		customerService.deleteAll();
		salesManService.deleteAll();
		salesService.deleteAll();
	}

}
