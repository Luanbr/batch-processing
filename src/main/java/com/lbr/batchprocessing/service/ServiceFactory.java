package com.lbr.batchprocessing.service;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.model.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private SalesmanService salesmanService;

	public IService create(Object item) {
		if (item instanceof Customer) {
			return customerService;
		}

		if (item instanceof Sale) {
			return saleService;
		}

		if (item instanceof Salesman) {
			return salesmanService;
		}

		return null;
	}
}
