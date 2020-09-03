package com.lbr.batchprocessing.batch.readers;

import java.io.IOException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.lbr.batchprocessing.batch.configuration.InputFileConfigProperties;

@Configuration
public class MultiResourcesReader {
	
	@Autowired
	private FlatFileItemReader<Object> reader;
	
	@Autowired
	private InputFileConfigProperties inputConfigProperties;

	@Bean
	public ItemReader<Object> multiResourceItemReader() throws IOException {
		final String inputDirectory = inputConfigProperties.getFile();
        final Resource[] resources = new PathMatchingResourcePatternResolver().getResources(inputDirectory);
		MultiResourceItemReader<Object> multiResourceItemReader = new MultiResourceItemReader<>();
		multiResourceItemReader.setResources(resources);
		multiResourceItemReader.setDelegate(reader);
		return multiResourceItemReader;
	}
}
