package com.lbr.batchprocessing.batch.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lbr.batchprocessing.batch.readers.SummarizeReader;
import com.lbr.batchprocessing.batch.writers.SummarizeWriter;
import com.lbr.batchprocessing.model.Summarize;

@Configuration
public class WriteStep {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SummarizeWriter summarizeWriter;
	
	@Autowired
	private SummarizeReader summarizeReader;

	@Bean
	public Step analyseAndWriteStep() {
		return stepBuilderFactory
				.get("analyseAndWriteStep")
				.<Object, Summarize>chunk(1)
				.reader(summarizeReader)
				.writer(summarizeWriter).build();
	}

}
