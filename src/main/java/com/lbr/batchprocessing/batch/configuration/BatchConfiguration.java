package com.lbr.batchprocessing.batch.configuration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.lbr.batchprocessing.batch.JobCompletionNotificationListener;
import com.lbr.batchprocessing.batch.Processor;
import com.lbr.batchprocessing.batch.mappers.CustomerMapper;
import com.lbr.batchprocessing.batch.mappers.SalesManMapper;
import com.lbr.batchprocessing.batch.mappers.SalesMapper;
import com.lbr.batchprocessing.batch.readers.SummarizeReader;
import com.lbr.batchprocessing.batch.tokenizer.CustomerTokenizer;
import com.lbr.batchprocessing.batch.tokenizer.SalesManTokenizer;
import com.lbr.batchprocessing.batch.tokenizer.SalesTokenizer;
import com.lbr.batchprocessing.batch.writers.LineMongoWriter;
import com.lbr.batchprocessing.batch.writers.SummarizeWriter;
import com.lbr.batchprocessing.model.Summarize;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	private final static String FORMART = "%s%s*";
	@Value("${io.input.file}")
	private Resource[] inputResources;
	@Value("${io.input.delimiter}")
	private String delimiter;
	@Value("${io.input.salesMan.lineId}")
	private String salesManLineId;
	@Value("${io.input.customer.lineId}")
	private String customerLineId;
	@Value("${io.input.sales.lineId}")
	private String salesLineId;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SalesMapper salesMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private SalesManMapper salesManMapper;
	@Autowired
	private LineMongoWriter lineMongoWriter;
	@Autowired
	private SummarizeWriter summarizeWriter;
	@Autowired
	private SummarizeReader summarizeReader;
	@Autowired
	private SalesManTokenizer salesManTokenizer;
	@Autowired
	private CustomerTokenizer customerTokenizer;
	@Autowired
	private SalesTokenizer salesTokenizer;

	@Bean
	public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper() {
		PatternMatchingCompositeLineMapper compositeLineMapper = new PatternMatchingCompositeLineMapper<Object>();
		Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
		tokenizers.put(String.format(FORMART, salesManLineId, delimiter), salesManTokenizer);
		tokenizers.put(String.format(FORMART, customerLineId, delimiter), customerTokenizer);
		tokenizers.put(String.format(FORMART, salesLineId, delimiter), salesTokenizer);

		compositeLineMapper.setTokenizers(tokenizers);

		Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(3);
		fieldSetMappers.put(String.format(FORMART, salesManLineId, delimiter), salesManMapper);
		fieldSetMappers.put(String.format(FORMART, customerLineId, delimiter), customerMapper);
		fieldSetMappers.put(String.format(FORMART, salesLineId, delimiter), salesMapper);
		compositeLineMapper.setFieldSetMappers(fieldSetMappers);

		return compositeLineMapper;
	}

	@Bean
	public FlatFileItemReader<Object> reader() {
		FlatFileItemReader<Object> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setLineMapper(patternMatchingCompositeLineMapper());
		return fileItemReader;
	}

	@Bean
	public ItemReader<Object> multiResourceItemReader() {
		MultiResourceItemReader<Object> multiResourceItemReader = new MultiResourceItemReader<>();
		multiResourceItemReader.setResources(inputResources);
		multiResourceItemReader.setDelegate(reader());
		return multiResourceItemReader;
	}

	@Bean
	<T> Processor<T> processor() {
		return new Processor<>();
	}
	
	@Bean
	public Job customJob(JobCompletionNotificationListener listener, Step readAndSaveOnMongoStep, Step analyseAndWriteStep) {
		return jobBuilderFactory.get("customJob")
				.listener(listener)
				.flow(readAndSaveOnMongoStep)
				.next(analyseAndWriteStep).end().build();
	}

	@Bean
	public Step readAndSaveOnMongoStep() {
		return stepBuilderFactory.get("readAndSaveOnMongoStep").<Object, Object>chunk(10).reader(multiResourceItemReader())
				.processor(processor()).writer(lineMongoWriter).build();
	}

	@Bean
	public Step analyseAndWriteStep() {
		return stepBuilderFactory.get("analyseAndWriteStep").<Object, Summarize>chunk(1).reader(summarizeReader)
				.writer(summarizeWriter).build();
	}

}
