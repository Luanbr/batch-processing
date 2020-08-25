package com.lbr.batchprocessing.writers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Sales;
import com.lbr.batchprocessing.service.SalesService;

@Component
public class SalesMongoWriter implements ItemWriter<Sales> {

	private static final Logger logger = LoggerFactory.getLogger(SalesMongoWriter.class);

	@Autowired
	private SalesService salesService;

	@Override
	public void write(List<? extends Sales> items) throws Exception {
		salesService.saveAll(items);
	}

	public void write(Sales item) throws Exception {
		List<Sales> sales = new ArrayList<>();
		sales.add(item);
		write(sales);
	}

}
