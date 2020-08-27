package com.lbr.batchprocessing.batch.writers;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
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
	private Summarize summarizeHeader;
	@Autowired
	private OutputFileConfigProperties configProperties;

	@Override
	public void write(List<? extends Summarize> items) throws Exception {
		FlatFileItemWriter<Summarize> writer = new FlatFileItemWriter<>();
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write(summarizeHeader.header());
			}
		});

		writer.setResource(new FileSystemResource(configProperties.getDir()
				+ DateUtils.localDateTimeToyyyyMMddHHmmss(LocalDateTime.now()) + configProperties.getExtension()));
		writer.setAppendAllowed(false);
		writer.setLineAggregator((summarize) -> {
			return summarize.getCustomersQuantity() + configProperties.getDelimiter() + summarize.getSellersQuantity()
					+ configProperties.getDelimiter() + summarize.getBiggestSale().getSaleId()
					+ configProperties.getDelimiter() + summarize.getWorstSeller().getSalesmanName();
		});
		writer.open(new ExecutionContext());
		writer.write(items);
		writer.close();
	}
}
