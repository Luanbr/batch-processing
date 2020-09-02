package com.lbr.batchprocessing.batch.tokenizer;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerTokenizer extends DelimitedLineTokenizer {

	public CustomerTokenizer(@Value("${io.input.delimiter}") final String delimiter) {
		this.setDelimiter(delimiter);
		this.setNames("id", "cnpj", "name", "businessArea");
	}
}
