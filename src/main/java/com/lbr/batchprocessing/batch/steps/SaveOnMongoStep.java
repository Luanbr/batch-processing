package com.lbr.batchprocessing.batch.steps;

import java.io.IOException;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lbr.batchprocessing.batch.configurations.InputFileConfigProperties;
import com.lbr.batchprocessing.batch.writers.LineMongoWriter;

@Configuration
public class SaveOnMongoStep {

	@Autowired
	private InputFileConfigProperties inputConfigProperties;

	@Autowired
	private ItemReader<Object> multiResourceItemReader;

	@Autowired
	private LineMongoWriter lineMongoWriter;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step readAndSaveOnMongoStep() throws IOException {
		return stepBuilderFactory.get("readAndSaveOnMongoStep").<Object, Object>chunk(inputConfigProperties.getChunk())
				.reader(multiResourceItemReader).faultTolerant().skip(FlatFileParseException.class)
				.skipLimit(inputConfigProperties.getSkipLimit()).writer(lineMongoWriter).build();
	}
}
