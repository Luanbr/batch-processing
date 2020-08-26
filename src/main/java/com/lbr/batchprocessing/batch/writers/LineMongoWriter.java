package com.lbr.batchprocessing.batch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.model.Sales;
import com.lbr.batchprocessing.model.SalesMan;

@Component
public class LineMongoWriter implements ItemWriter<Object> {
	private static final Logger logger = LoggerFactory.getLogger(LineMongoWriter.class);

	@Autowired
	private CustomerMongoWriter customerWriter;
	@Autowired
	private SalesManMongoWriter salesManWriter;
	@Autowired
	private SalesMongoWriter salesWriter;

	@Override
	public void write(List<? extends Object> items) throws Exception {
		items.forEach(item -> {
			try {
				if (item instanceof Customer) {
					customerWriter.write((Customer) item);
				} else if (item instanceof SalesMan) {
					salesManWriter.write((SalesMan) item);
				} else if (item instanceof Sales) {
					salesWriter.write((Sales) item);
				}
			} catch (Exception e) {
				logger.error("Error in LineMongoWriter ", e);
			}
		});
	}

}
