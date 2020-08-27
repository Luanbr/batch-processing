package com.lbr.batchprocessing.batch.writers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Salesman;
import com.lbr.batchprocessing.service.SalesmanService;

@Component
public class SalesmanMongoWriter implements ItemWriter<Salesman>{

	@Autowired
	private SalesmanService salesManService;
	
	@Override
	public void write(List<? extends Salesman> items) throws Exception {
		salesManService.saveAll(items);
	}
	
	public void write(Salesman item) throws Exception {
		List<Salesman> sellers = new ArrayList<>();
		sellers.add(item);
		write(sellers);
	}
	
}
