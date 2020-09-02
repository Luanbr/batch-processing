package com.lbr.batchprocessing.batch.writers;

import java.util.List;

import com.lbr.batchprocessing.service.IService;
import com.lbr.batchprocessing.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineMongoWriter implements ItemWriter<Object> {
	private static final Logger logger = LoggerFactory.getLogger(LineMongoWriter.class);

	@Autowired
	private ServiceFactory serviceFactory;

	@Override
	public void write(List<?> items) {
		items.forEach(item -> {
			try {
				final IService service = serviceFactory.create(item);
				service.save(item);
			} catch (Exception e) {
				logger.error("Error in LineMongoWriter ", e);
			}
		});
	}
}
