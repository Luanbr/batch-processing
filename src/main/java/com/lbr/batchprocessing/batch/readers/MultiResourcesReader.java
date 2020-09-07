package com.lbr.batchprocessing.batch.readers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.lbr.batchprocessing.batch.configurations.InputFileConfigProperties;

@Configuration
public class MultiResourcesReader {
	private static final Logger logger = LoggerFactory.getLogger(MultiResourcesReader.class);
	
	@Autowired
	private FlatFileItemReader<Object> reader;
	
	@Autowired
	private InputFileConfigProperties inputConfigProperties;

	@Bean
	public ItemReader<Object> multiResourceItemReader() throws IOException {
		try {
			final String inputDirectory = inputConfigProperties.getFile();
			final Resource[] resources = new PathMatchingResourcePatternResolver().getResources(inputDirectory);
			MultiResourceItemReader<Object> multiResourceItemReader = new MultiResourceItemReader<>();
			multiResourceItemReader.setResources(resources);
			multiResourceItemReader.setDelegate(reader);
			return multiResourceItemReader;
		} catch (IOException e) {
			logger.error("Error while reading input directory ", e);
			throw e;
		}
	}
}
