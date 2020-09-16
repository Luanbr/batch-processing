package com.lbr.batchprocessing.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Configuration
public class ReadFileJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private Step readAndSaveSummaryInMemoryStep;
	
	@Autowired
	private Step analyseAndWriteStep;
	
	@Autowired
	private Step cleanSummaryStep;

	@Bean
	public Job readFilesJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("readFilesJob")
				.listener(listener)
				.flow(cleanSummaryStep)
				.next(readAndSaveSummaryInMemoryStep)
				.next(analyseAndWriteStep).end().build();
	}
}
