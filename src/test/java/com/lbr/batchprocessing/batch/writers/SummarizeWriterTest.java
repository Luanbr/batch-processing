package com.lbr.batchprocessing.batch.writers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SummarizeWriterTest {

	@Autowired
	private SummarizeWriter summarizeWriter;
	
	@Test
	public void whenErrorOccur_thenReturnException() {
		assertThatThrownBy(() -> {
			summarizeWriter.write(null);
		}).isInstanceOf(Exception.class);
	}
}
