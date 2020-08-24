package com.lbr.batchprocessing.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.service.CustomerService;

@Component
public class CustomerMongoWriter implements ItemWriter<Customer>{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public void write(List<? extends Customer> items) throws Exception {
		customerService.saveAll(items);
	}
	
}
