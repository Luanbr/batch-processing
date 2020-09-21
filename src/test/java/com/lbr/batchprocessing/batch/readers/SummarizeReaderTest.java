package com.lbr.batchprocessing.batch.readers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.lbr.batchprocessing.model.Summarize;

@SpringBootTest
@ActiveProfiles("test")
public class SummarizeReaderTest {

	@Autowired
	private SummarizeReader summarizeReader;
	
	@Autowired
	private Summarize summarize;
	
	@Test
	public void whenReadAndSummarizeEmpty_thenReturnNull() {
		Summarize summarizeReturned = summarizeReader.read();
		assertThat(summarizeReturned).isNull();
	}
	
	@Test
	public void whenReadAndSummarizeNotEmpty_thenNotReturnNull() {
		summarize.getSalesmanTotalSalesMap().put("VendendorName", 300.0);
		Summarize summarizeReturned = summarizeReader.read();
		assertThat(summarizeReturned).isNotNull();
	}

	@Test
	public void whenErrorOccurDuringRead_thenReturnException() {
		summarize.addCustomersCount();
		assertThatThrownBy(() -> {
			summarizeReader.read();
		}).isInstanceOf(Exception.class);
	}
	
	@AfterEach
	public void clean() {
		summarize.clean();
	}
}
