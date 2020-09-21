package com.lbr.batchprocessing.batch.steps;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.lbr.batchprocessing.service.SummarizeService;

@SpringBootTest
@ActiveProfiles("test")
public class CleanSummaryInMemoryTaskletTest {
	
	@MockBean
	private SummarizeService summarizeService;
	
	@Autowired
	private CleanSummaryInMemoryTasklet cleanSummaryInMemoryTasklet;
	
	@Test
	public void whenErrorOccurDuringExecute_thenReturnException() { 
		doThrow(NullPointerException.class)
			.when(summarizeService)
			.clean();

		assertThatThrownBy(() -> {
			cleanSummaryInMemoryTasklet.execute(null, null);
		}).isInstanceOf(Exception.class);
	}
}
