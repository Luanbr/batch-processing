package com.lbr.batchprocessing.batch.writers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.batch.configuration.OutputFileConfigProperties;
import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.utils.DateUtils;

@Component
public class SummarizeWriter implements ItemWriter<Summarize> {

	@Autowired
	private OutputFileConfigProperties configProperties;

	@Override
	public void write(List<? extends Summarize> items) throws Exception {
		FlatFileItemWriter<Summarize> fileItemWriter = new FlatFileItemWriter<>();

		final String today = DateUtils.localDateTimeToyyyyMMddHHmmss(LocalDateTime.now());
		final String outputDir = configProperties.getDir();
		final String extensionFile = configProperties.getExtension();

		fileItemWriter.setResource(new FileSystemResource(outputDir + today + extensionFile));
		fileItemWriter.setAppendAllowed(false);

		fileItemWriter.setLineAggregator(summarize -> {
			final List<String> line = Arrays.asList(Objects.toString(summarize.getCustomersQuantity(), ""),
				Objects.toString(summarize.getSellersQuantity(), ""),
				Objects.toString(summarize.getBiggestSale().getSaleId(), ""),
				Objects.toString(summarize.getWorstSeller().getSalesmanName(), "")
			);
			return String.join(configProperties.getDelimiter(), line);
		});

		fileItemWriter.open(new ExecutionContext());
		fileItemWriter.write(items);
		fileItemWriter.close();
	}
}
