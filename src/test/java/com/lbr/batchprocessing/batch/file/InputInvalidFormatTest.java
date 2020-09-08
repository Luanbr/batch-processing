package com.lbr.batchprocessing.batch.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = { "io.input.file=classpath:/data/in/input_invalid_format.dat",
		"io.input.skipLimit=2" })
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
public class InputInvalidFormatTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Before
	public void setup() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	public void whenInputFileWithInvalidFormatAndExceededSkipLimit_thenReadFilesJobReturnFailed() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertThat(jobExecution.getJobInstance().getJobName()).isEqualTo("readFilesJob");
		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("FAILED");
		assertThatThrownBy(() -> {
			throw jobExecution.getAllFailureExceptions().get(0);
		})
			.isInstanceOf(SkipLimitExceededException.class)
			.hasMessage("Skip limit of '2' exceeded");
	}
}
