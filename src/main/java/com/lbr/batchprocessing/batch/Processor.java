package com.lbr.batchprocessing.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;



public class Processor<T> implements ItemProcessor<T, T>{

	private static final Logger logger = LoggerFactory.getLogger(Processor.class);

	@Override
	public T process(T item) throws Exception {
		logger.info("PROCESSOR ITEM ----> " + item.toString());
		return item;
	};
}
