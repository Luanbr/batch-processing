package com.lbr.batchprocessing.batch.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.model.WorstSeller;
import com.lbr.batchprocessing.service.CustomerService;
import com.lbr.batchprocessing.service.SalesManService;
import com.lbr.batchprocessing.service.SalesService;

@Component
public class SummarizeReader implements ItemReader<Summarize> {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private SalesService salesService;
	@Autowired
	private SalesManService salesManService;

	@Override
	public Summarize read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Summarize summarize = create();
		clean();
		return !summarize.isEmpty() ? summarize : null;
	}

	public Summarize create() {
		Long customersQuantity = customerService.countCustomerDistinctByCnpj();
		Long sellersQuantity = salesManService.countSalesManDistinctByCnpj();
		BiggestSale biggestSale = salesService.findBiggestSale();
		WorstSeller worstSeller = salesService.findWorstSeller();
		Summarize summarize = new Summarize(customersQuantity, sellersQuantity, biggestSale, worstSeller);
		return summarize;
	}

	public void clean() {
		customerService.deleteAll();
		salesManService.deleteAll();
		salesService.deleteAll();
	}

}
