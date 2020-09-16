package com.lbr.batchprocessing.batch.scheduler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lbr.batchprocessing.utils.DateUtils;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Configuration
@Profile("!test")
@EnableScheduling
public class RunJobScheduler {
	private static final Logger logger = LoggerFactory.getLogger(RunJobScheduler.class);
			
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Scheduled(fixedDelayString = "${batch.schedulerInMilliseconds}")
	public void runScheduled() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		try {
			jobLauncher.run(job, new JobParametersBuilder()
					.addString("JobID", DateUtils.localDateTimeToyyyyMMddHHmmss(LocalDateTime.now()))
					.toJobParameters());
		} catch (Exception e) {
			logger.error("Error while running the job! ", e);
			throw e;
		}
	}
}
