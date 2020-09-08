package com.lbr.batchprocessing.batch.file;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.lbr.batchprocessing.batch.configurations.OutputFileConfigProperties;
import com.lbr.batchprocessing.utils.FileUtils;

@TestPropertySource(properties = { "io.input.file=classpath:/data/in/input_validSmallFile.dat" })
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
public class InputValidSmallFileTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Autowired
	private OutputFileConfigProperties outputFileConfigProperties;

	@Before
	public void setup() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	@Test
	public void whenInputFileWithValidFormat_thenReadFilesJobReturnCompleted() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertThat(jobExecution.getJobInstance().getJobName()).isEqualTo("readFilesJob");
		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
	}

	@Test
	public void whenInputFileWithValidFormat_thenWriteCorrectSummary() throws Exception {
		String expectedSummary = String.join(outputFileConfigProperties.getDelimiter(),
				(Arrays.asList("2", "3", "7", "Jonas")));
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		Path fileName = Files.walk(Paths.get(outputFileConfigProperties.getDir()))
				.filter(s -> s.toString().endsWith(outputFileConfigProperties.getExtension())).map(Path::getFileName)
				.collect(Collectors.toList()).get(0);

		Path outputFile = Paths.get(outputFileConfigProperties.getDir() + File.separator + fileName);
		String actualSummary = Files.lines(outputFile).collect(Collectors.toList()).get(0);

		assertThat(new ByteArrayInputStream(actualSummary.getBytes()))
				.hasSameContentAs(new ByteArrayInputStream(expectedSummary.getBytes()));
		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
	}

	@AfterEach
	public void deleteOutput() throws IOException {
		FileUtils.deleteOutputFiles(outputFileConfigProperties.getDir());
	}

}
