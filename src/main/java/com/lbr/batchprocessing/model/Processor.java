package com.lbr.batchprocessing.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;



public class Processor<T> implements ItemProcessor<T, String>{

	private static final Logger logger = LoggerFactory.getLogger(Processor.class);

	@Override
	public String process(T item) throws Exception {
		logger.info("PROCESSOR ITEM ----> " + item.toString());
		return item.toString();
	};
}
