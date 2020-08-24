package com.lbr.batchprocessing.writers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Customer;

@Component
public class LineMongoWriter implements ItemWriter<Object> {

	@Autowired
	private CustomerMongoWriter customerWriter;

	@Override
	public void write(List<? extends Object> items) throws Exception {
		items.forEach(item -> {
			try {
				if (item instanceof Customer) {
					List<Customer> customers = new ArrayList<>();
					customers.add((Customer) item);
					customerWriter.write(customers);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
