package com.lbr.batchprocessing.batch.writers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.batch.configurations.OutputFileConfigProperties;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.service.SummarizeService;
import com.lbr.batchprocessing.utils.DateUtils;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class SummarizeWriter implements ItemWriter<Summarize> {
	private static final Logger logger = LoggerFactory.getLogger(SummarizeWriter.class);

	@Autowired
	private OutputFileConfigProperties configProperties;
	
	@Autowired
	private SummarizeService summarizeService;

	@Override
	public void write(List<? extends Summarize> items) throws Exception {
		logger.info("Writing summary file!");
		FlatFileItemWriter<Summarize> fileItemWriter = new FlatFileItemWriter<>();
		try {

			final String today = DateUtils.localDateTimeToyyyyMMddHHmmss(LocalDateTime.now());
			final String outputDir = configProperties.getDir();
			final String extensionFile = configProperties.getExtension();

			fileItemWriter.setResource(new FileSystemResource(outputDir + today + extensionFile));
			fileItemWriter.setAppendAllowed(false);

			fileItemWriter.setLineAggregator(summarize -> {
				final List<String> line = Arrays.asList(Objects.toString(summarize.getCustomersQuantity(), ""),
						Objects.toString(summarize.getSellersQuantity(), ""),
						Objects.toString(summarize.getBiggestSale().getSaleId(), ""),
						Objects.toString(summarize.getWorstSeller().getSalesmanName(), ""));
				return String.join(configProperties.getDelimiter(), line);
			});
	
			fileItemWriter.open(new ExecutionContext());
			fileItemWriter.write(items);
			summarizeService.clean();
		} catch (Exception e) {
			logger.error("Error writing summary file!", e);
			throw e; 
		} finally {
			fileItemWriter.close();
		}
	}
}
