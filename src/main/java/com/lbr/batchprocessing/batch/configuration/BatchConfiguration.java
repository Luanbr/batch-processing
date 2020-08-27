package com.lbr.batchprocessing.batch.configuration;

import java.util.HashMap;
import java.util.Map;

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
import com.lbr.batchprocessing.batch.mappers.CustomerMapper;
import com.lbr.batchprocessing.batch.mappers.SaleMapper;
import com.lbr.batchprocessing.batch.mappers.SalesmanMapper;
import com.lbr.batchprocessing.batch.readers.SummarizeReader;
import com.lbr.batchprocessing.batch.tokenizer.CustomerTokenizer;
import com.lbr.batchprocessing.batch.tokenizer.SaleTokenizer;
import com.lbr.batchprocessing.batch.tokenizer.SalesmanTokenizer;
import com.lbr.batchprocessing.batch.writers.LineMongoWriter;
import com.lbr.batchprocessing.batch.writers.SummarizeWriter;
import com.lbr.batchprocessing.model.Summarize;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private final static String FORMART = "%s%s*";
	@Value("${io.input.file}")
	private Resource[] inputResources;
	@Autowired
	private InputFileConfigProperties configProperties;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SaleMapper salesMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private SalesmanMapper salesManMapper;
	@Autowired
	private LineMongoWriter lineMongoWriter;
	@Autowired
	private SummarizeWriter summarizeWriter;
	@Autowired
	private SummarizeReader summarizeReader;
	@Autowired
	private SalesmanTokenizer salesManTokenizer;
	@Autowired
	private CustomerTokenizer customerTokenizer;
	@Autowired
	private SaleTokenizer salesTokenizer;

	@Bean
	public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper() {
		PatternMatchingCompositeLineMapper compositeLineMapper = new PatternMatchingCompositeLineMapper<Object>();
		Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
		tokenizers.put(String.format(FORMART, configProperties.getSalesmanLineId(), configProperties.getDelimiter()),
				salesManTokenizer);
		tokenizers.put(String.format(FORMART, configProperties.getCustomerLineId(), configProperties.getDelimiter()),
				customerTokenizer);
		tokenizers.put(String.format(FORMART, configProperties.getSaleLineId(), configProperties.getDelimiter()),
				salesTokenizer);

		compositeLineMapper.setTokenizers(tokenizers);

		Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(3);
		fieldSetMappers.put(
				String.format(FORMART, configProperties.getSalesmanLineId(), configProperties.getDelimiter()),
				salesManMapper);
		fieldSetMappers.put(
				String.format(FORMART, configProperties.getCustomerLineId(), configProperties.getDelimiter()),
				customerMapper);
		fieldSetMappers.put(String.format(FORMART, configProperties.getSaleLineId(), configProperties.getDelimiter()),
				salesMapper);
		compositeLineMapper.setFieldSetMappers(fieldSetMappers);

		return compositeLineMapper;
	}

	@Bean
	public FlatFileItemReader<Object> reader() {
		FlatFileItemReader<Object> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setEncoding("UTF-8");
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
	public Job readFilesJob(JobCompletionNotificationListener listener, Step readAndSaveOnMongoStep,
			Step analyseAndWriteStep) {
		return jobBuilderFactory.get("readFilesJob").listener(listener).flow(readAndSaveOnMongoStep)
				.next(analyseAndWriteStep).end().build();
	}

	@Bean
	public Step readAndSaveOnMongoStep() {
		return stepBuilderFactory.get("readAndSaveOnMongoStep").<Object, Object>chunk(configProperties.getChunk())
				.reader(multiResourceItemReader()).writer(lineMongoWriter).build();
	}

	@Bean
	public Step analyseAndWriteStep() {
		return stepBuilderFactory.get("analyseAndWriteStep").<Object, Summarize>chunk(1).reader(summarizeReader)
				.writer(summarizeWriter).build();
	}

}
