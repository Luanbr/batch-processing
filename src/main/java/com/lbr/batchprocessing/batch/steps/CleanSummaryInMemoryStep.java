package com.lbr.batchprocessing.batch.steps;

import java.io.IOException;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Configuration
public class CleanSummaryInMemoryStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private CleanSummaryInMemoryTasklet cleanSummaryInMemoryTasklet; 
	
	@Bean
	public Step cleanSummaryStep() throws IOException {
		return stepBuilderFactory
				.get("cleanSummaryStep")
				.tasklet(cleanSummaryInMemoryTasklet)
				.build();
	}
}
