package com.lbr.batchprocessing.batch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.service.IItemService;
import com.lbr.batchprocessing.service.ServiceFactory;

@Component
public class LineMongoWriter implements ItemWriter<Object> {
	private static final Logger logger = LoggerFactory.getLogger(LineMongoWriter.class);

	@Autowired
	private ServiceFactory serviceFactory;

	@Override
	public void write(List<?> items) {
		logger.info("Writing " + items.size() + " items on MongoDB!!!");
		items.forEach(item -> {
			try {
				final IItemService service = serviceFactory.create(item);
				service.save(item);
			} catch (Exception e) {
				logger.error("Error while writing on MongoDB ", e);
			}
		});
		logger.info("Writing on MongoDB Ended!!!");
	}
}
