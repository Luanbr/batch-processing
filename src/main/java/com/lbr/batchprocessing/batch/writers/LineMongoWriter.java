package com.lbr.batchprocessing.batch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineMongoWriter implements ItemWriter<Object> {
	private static final Logger logger = LoggerFactory.getLogger(LineMongoWriter.class);
	@Autowired
	private LineWriterFactory lineWriterFactory;

	@Override
	public void write(List<? extends Object> items) throws Exception {
		items.forEach(item -> {
			try {
				lineWriterFactory.getInstance(item).write(item);
			} catch (Exception e) {
				logger.error("Error in LineMongoWriter ", e);
			}
		});
	}

}
