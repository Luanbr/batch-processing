package com.lbr.batchprocessing.batch.policy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.batch.configuration.InputFileConfigProperties;

@Component
public class LineSkipPolicy implements SkipPolicy{

	@Autowired
	private InputFileConfigProperties configProperties;
	
	@Override
	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		if(t instanceof FlatFileParseException && skipCount < configProperties.getSkipLimit()) {
			return true;
		}
		return false;
	}

}
