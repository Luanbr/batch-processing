package com.lbr.batchprocessing.batch.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineReader {
	
	@Autowired
	private LineMapper<Object> patternMatchingCompositeLineMapper;
	
	@Bean
	public FlatFileItemReader<Object> reader() {
		FlatFileItemReader<Object> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setEncoding("UTF-8");
		fileItemReader.setLineMapper(patternMatchingCompositeLineMapper);
		return fileItemReader;
	}
}
