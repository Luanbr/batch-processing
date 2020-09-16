package com.lbr.batchprocessing.batch.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.service.SummarizeService;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class SummarizeReader implements ItemReader<Summarize> {
	private static final Logger logger = LoggerFactory.getLogger(SummarizeReader.class);
	
	@Autowired
	private SummarizeService summarizeService;
	
	@Autowired
	private Summarize summarize;

	@Override
	public Summarize read() {
		try {
			if(summarize.isEmpty())
				return null;
			summarizeService.updateWorstSeller();
			return summarize;
		} catch (Exception e) {
			logger.error("Error while reading Summarize from Memory", e);
			throw e;
		}
	}
}
