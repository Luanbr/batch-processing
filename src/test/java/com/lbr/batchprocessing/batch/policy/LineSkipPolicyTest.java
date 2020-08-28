package com.lbr.batchprocessing.batch.policy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbr.batchprocessing.batch.configuration.InputFileConfigProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LineSkipPolicyTest {

	@Autowired
	private LineSkipPolicy policy;

	@MockBean
	private InputFileConfigProperties properties;

	@Test
	public void whenShouldSkip_FlatFileParseExceptionAndCountMinorThatSkipLimit_thenReturnTrue() {
		int skipCount = 1;
		FlatFileParseException e = new FlatFileParseException(null, null);
		when(properties.getSkipLimit()).thenReturn(2L);
		assertTrue(policy.shouldSkip(e, skipCount));
	}

	@Test
	public void whenShouldSkip_FlatFileParseExceptionAndCountBiggerThanSkipLimit_thenReturnFalse() {
		int skipCount = 2;
		FlatFileParseException exception = new FlatFileParseException(null, null);
		when(properties.getSkipLimit()).thenReturn(1L);
		assertFalse(policy.shouldSkip(exception, skipCount));
	}

	@Test
	public void whenShouldSkip_WithNotFlatFileParseException_thenReturnFalse() {
		int skipCount = 2;
		Exception exception = new Exception();
		when(properties.getSkipLimit()).thenReturn(1L);
		assertFalse(policy.shouldSkip(exception, skipCount));
	}

}
