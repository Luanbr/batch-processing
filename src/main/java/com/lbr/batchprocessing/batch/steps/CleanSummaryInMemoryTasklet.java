package com.lbr.batchprocessing.batch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.service.SummarizeService;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class CleanSummaryInMemoryTasklet implements Tasklet {
	private static final Logger logger = LoggerFactory.getLogger(CleanSummaryInMemoryTasklet.class);
	
	@Autowired
	private SummarizeService summarizeService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			summarizeService.clean();
			return RepeatStatus.FINISHED;
		} catch (Exception e) {
			logger.error("Error while clean Summarize from Memory", e);
			throw e;
		}
	}

}
