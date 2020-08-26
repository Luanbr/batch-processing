package com.lbr.batchprocessing.batch.tokenizer;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SalesTokenizer extends DelimitedLineTokenizer {

	public SalesTokenizer(@Value("${io.input.delimiter}") final String delimiter) {
		this.setDelimiter(delimiter);
		this.setNames(new String[] { "id", "salesId", "items", "salesManName" });
	}
}
