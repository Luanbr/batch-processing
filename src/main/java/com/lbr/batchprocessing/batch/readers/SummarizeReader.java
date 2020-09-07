package com.lbr.batchprocessing.batch.readers;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.service.SummarizeService;

@Component
public class SummarizeReader implements ItemReader<Summarize> {
	private static final Logger logger = LoggerFactory.getLogger(SummarizeReader.class);
	@Autowired
	private SummarizeService summarizeService;

	@Override
	public Summarize read() {
		try {
			Summarize summarize = summarizeService.create();
			if (Objects.nonNull(summarize))
				summarizeService.clean();
			return summarize;
		} catch (Exception e) {
			logger.error("Error while reading Summarize from MongoDB", e);
			throw e;
		}
	}
}
