package com.lbr.batchprocessing.batch.tokenizers;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SalesmanTokenizer extends DelimitedLineTokenizer {

	public SalesmanTokenizer(@Value("${io.input.delimiter}") final String delimiter) {
		this.setDelimiter(delimiter);
		this.setNames("id", "cpf", "name", "salary");
	}
}
