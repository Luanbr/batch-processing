package com.lbr.batchprocessing.batch.readers;

import java.util.Objects;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.service.SummarizeService;

@Component
public class SummarizeReader implements ItemReader<Summarize> {

	@Autowired
	private SummarizeService summarizeService;

	@Override
	public Summarize read() {
		Summarize summarize = summarizeService.create();
		if (Objects.isNull(summarize))
			return summarize;
		summarizeService.clean();
		return summarize;
	}
}
