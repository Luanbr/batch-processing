package com.lbr.batchprocessing.writers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.SalesMan;
import com.lbr.batchprocessing.service.SalesManService;

@Component
public class SalesManMongoWriter implements ItemWriter<SalesMan>{

	private static final Logger logger = LoggerFactory.getLogger(SalesManMongoWriter.class);
	
	@Autowired
	private SalesManService salesManService;
	
	@Override
	public void write(List<? extends SalesMan> items) throws Exception {
		salesManService.saveAll(items);
	}
	
	public void write(SalesMan item) throws Exception {
		List<SalesMan> sellers = new ArrayList<>();
		sellers.add(item);
		write(sellers);
	}
	
}
