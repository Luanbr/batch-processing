package com.lbr.batchprocessing.batch.writers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.service.CustomerService;

@Component
public class CustomerMongoWriter implements ItemWriter<Customer>{

	private static final Logger logger = LoggerFactory.getLogger(CustomerMongoWriter.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public void write(List<? extends Customer> items) throws Exception {
		customerService.saveAll(items);
	}
	
	public void write(Customer item) throws Exception {
		List<Customer> customers = new ArrayList<>();
		customers.add(item);
		write(customers);
	}
	
}
