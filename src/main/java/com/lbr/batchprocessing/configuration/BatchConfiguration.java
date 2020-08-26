package com.lbr.batchprocessing.configuration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.lbr.batchprocessing.mappers.CustomerMapper;
import com.lbr.batchprocessing.mappers.SalesManMapper;
import com.lbr.batchprocessing.mappers.SalesMapper;
import com.lbr.batchprocessing.model.JobCompletionNotificationListener;
import com.lbr.batchprocessing.model.Processor;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.readers.SummarizeReader;
import com.lbr.batchprocessing.writers.LineMongoWriter;
import com.lbr.batchprocessing.writers.SummarizeWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
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
	@Value("${input.file}")
	private Resource[] inputResources;
	@Autowired
	private LineMongoWriter lineMongoWriter;
	@Autowired
	private SummarizeWriter summarizeWriter;
	@Autowired
	private SummarizeReader summarizeReader;
	

	@Bean
	public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper() {
		PatternMatchingCompositeLineMapper compositeLineMapper = new PatternMatchingCompositeLineMapper<Object>();

		Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
		tokenizers.put("001*", salesManTokenizer());
		tokenizers.put("002*", customerTokenizer());
		tokenizers.put("003*", salesTokenizer());

		compositeLineMapper.setTokenizers(tokenizers);

		Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(3);
		fieldSetMappers.put("001*", salesManMapper);
		fieldSetMappers.put("002*", customerMapper);
		fieldSetMappers.put("003*", salesMapper);
		compositeLineMapper.setFieldSetMappers(fieldSetMappers);

		return compositeLineMapper;
	}

	@Bean
	public LineTokenizer customerTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("ç");
		lineTokenizer.setNames(new String[] { "id", "cnpj", "name", "businessArea" });
		return lineTokenizer;
	}

	@Bean
	public LineTokenizer salesManTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("ç");
		lineTokenizer.setNames(new String[] { "id", "cpf", "name", "salary" });
		return lineTokenizer;
	}

	@Bean
	public LineTokenizer salesTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("ç");
		lineTokenizer.setNames(new String[] { "id", "salesId", "items", "salesManName" });
		return lineTokenizer;
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
	public Job job(JobCompletionNotificationListener listener, Step step1, Step step2) {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.next(step2)
				.end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Object, Object>chunk(2)
				.reader(multiResourceItemReader())
				.processor(processor())
				.writer(lineMongoWriter).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Object, Summarize>chunk(1)
				.reader(summarizeReader)
				.writer(summarizeWriter).build();
	}

}
