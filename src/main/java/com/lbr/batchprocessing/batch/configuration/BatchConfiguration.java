package com.lbr.batchprocessing.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lbr.batchprocessing.batch.JobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private Step readAndSaveOnMongoStep;
	@Autowired
	private Step analyseAndWriteStep;

	@Bean
	public Job readFilesJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("readFilesJob").listener(listener).flow(readAndSaveOnMongoStep)
				.next(analyseAndWriteStep).end().build();
	}
}
