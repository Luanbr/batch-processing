package com.lbr.batchprocessing.batch.policy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbr.batchprocessing.batch.configuration.InputFileConfigProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LineSkipPolicyTest {

	@Autowired
	private LineSkipPolicy policy;

	@Autowired
	private InputFileConfigProperties properties;

	@Test
	public void whenShouldSkip_FlatFileParseExceptionAndCountMinorThatSkipLimit_thenReturnTrue() {
		int skipCount = (int) (properties.getSkipLimit() - 1);
		FlatFileParseException e = new FlatFileParseException(null, null);
		assertTrue(policy.shouldSkip(e, skipCount));
	}

	@Test
	public void whenShouldSkip_FlatFileParseExceptionAndCountBiggerThanSkipLimit_thenReturnFalse() {
		int skipCount = (int) (properties.getSkipLimit() + 1);
		FlatFileParseException exception = new FlatFileParseException(null, null);
		assertFalse(policy.shouldSkip(exception, skipCount));
	}

	@Test
	public void whenShouldSkip_WithNotFlatFileParseException_thenReturnFalse() {
		int skipCount = (int) (properties.getSkipLimit() + 1);
		Exception exception = new Exception();
		assertFalse(policy.shouldSkip(exception, skipCount));
	}

}
