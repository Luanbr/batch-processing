package com.lbr.batchprocessing.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbr.batchprocessing.BatchProcessingApplication;

@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { BatchProcessingApplication.class })
public class BatchIntegrationTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Before
	public void setup() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	public void testJob() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
		assertEquals("readFilesJob", jobExecution.getJobInstance().getJobName());
	}
}
