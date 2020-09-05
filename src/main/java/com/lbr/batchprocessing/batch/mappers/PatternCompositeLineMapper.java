package com.lbr.batchprocessing.batch.mappers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lbr.batchprocessing.batch.configurations.InputFileConfigProperties;
import com.lbr.batchprocessing.batch.tokenizers.CustomerTokenizer;
import com.lbr.batchprocessing.batch.tokenizers.SaleTokenizer;
import com.lbr.batchprocessing.batch.tokenizers.SalesmanTokenizer;

@Configuration
public class PatternCompositeLineMapper {

	private final static String FORMART = "%s%s*";
	@Autowired
	private InputFileConfigProperties inputConfigProperties;
	@Autowired
	private SalesmanTokenizer salesmanTokenizer;
	@Autowired
	private CustomerTokenizer customerTokenizer;
	@Autowired
	private SaleTokenizer salesTokenizer;
	@Autowired
	private SaleMapper salesMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private SalesmanMapper salesmanMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper() {
		PatternMatchingCompositeLineMapper compositeLineMapper = new PatternMatchingCompositeLineMapper<Object>();
		Map<String, LineTokenizer> tokenizers = getMapTokenizers();
		Map<String, FieldSetMapper> fieldSetMappers = getMapFieldSetMappers();

		compositeLineMapper.setTokenizers(tokenizers);
		compositeLineMapper.setFieldSetMappers(fieldSetMappers);

		return compositeLineMapper;
	}

	@SuppressWarnings("rawtypes")
	private Map<String, FieldSetMapper> getMapFieldSetMappers() {
		String delimiter = inputConfigProperties.getDelimiter();
		Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(3);
		fieldSetMappers.put(String.format(FORMART, inputConfigProperties.getSalesmanLineId(), delimiter),
				salesmanMapper);
		fieldSetMappers.put(String.format(FORMART, inputConfigProperties.getCustomerLineId(), delimiter),
				customerMapper);
		fieldSetMappers.put(String.format(FORMART, inputConfigProperties.getSaleLineId(), delimiter), salesMapper);
		return fieldSetMappers;
	}

	private Map<String, LineTokenizer> getMapTokenizers() {
		String delimiter = inputConfigProperties.getDelimiter();
		Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
		tokenizers.put(String.format(FORMART, inputConfigProperties.getSalesmanLineId(), delimiter), salesmanTokenizer);
		tokenizers.put(String.format(FORMART, inputConfigProperties.getCustomerLineId(), delimiter), customerTokenizer);
		tokenizers.put(String.format(FORMART, inputConfigProperties.getSaleLineId(), delimiter), salesTokenizer);
		return tokenizers;
	}
}
