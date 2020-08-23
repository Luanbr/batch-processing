package com.lbr.batchprocessing.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.lbr.batchprocessing.mappers.CustomerMapper;
import com.lbr.batchprocessing.mappers.SalesManMapper;
import com.lbr.batchprocessing.mappers.SalesMapper;
import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.model.JobCompletionNotificationListener;
import com.lbr.batchprocessing.model.Processor;
import com.lbr.batchprocessing.model.Sales;
import com.lbr.batchprocessing.model.SalesMan;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	public SalesMapper salesMapper;
	@Autowired
	public CustomerMapper customerMapper;
	@Autowired
	public SalesManMapper salesManMapper;

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
	public ItemReader<Object> reader() {
		FlatFileItemReader<Object> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setResource(new ClassPathResource("test.dat"));
		fileItemReader.setLineMapper(patternMatchingCompositeLineMapper());

		return fileItemReader;
	}

	@Bean
	<T> Processor<T> processor() {
		return new Processor<>();
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(FlatFileItemWriter writer) {
		return stepBuilderFactory.get("step1")
				.<Object, String>chunk(2)
				.reader(reader())
				.processor(processor())
				.writer(writer).build();
	}

//	@Bean
//    public FlatFileItemWriter<Cliente> writer() 
//    {
//        //Create writer instance
//        FlatFileItemWriter<Cliente> writer = new FlatFileItemWriter<>();
//         
//        //Set output file location
//        writer.setResource(new ClassPathResource("output/outputData.csv"));
//         
//        //All job repetitions should "append" to same output file
//        writer.setAppendAllowed(true);
//        
//      //Name field values sequence based on object properties 
//        writer.setLineAggregator(new DelimitedLineAggregator<Cliente>() {
//            {
//                setDelimiter(",");
//                setFieldExtractor(new BeanWrapperFieldExtractor<Cliente>() {
//                    {
//                        setNames(new String[] { "id" });
//                    }
//                });
//            }
//        });
//         
//        return writer;
//    }

	@Bean
	public FlatFileItemWriter writer() {
		return new FlatFileItemWriterBuilder<String>().name("writer")
				.resource(new FileSystemResource("target/test-outputs/output.txt"))
				.lineAggregator(new PassThroughLineAggregator<>()).build();
	}
}