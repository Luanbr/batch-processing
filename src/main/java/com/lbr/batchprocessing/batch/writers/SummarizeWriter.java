package com.lbr.batchprocessing.batch.writers;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Summarize;
import com.lbr.batchprocessing.utils.DateUtils;

@Component
public class SummarizeWriter implements ItemWriter<Summarize> {

	private static final Logger logger = LoggerFactory.getLogger(SummarizeWriter.class);

	@Value("${io.output.dir}")
	private String outputDir;
	@Value("${io.output.type}")
	private String outputType;
	@Value("${io.output.delimiter}")
	private String delimiter;
	@Autowired
	private Summarize summarizeHeader;

	@Override
	public void write(List<? extends Summarize> items) throws Exception {
		FlatFileItemWriter<Summarize> writer = new FlatFileItemWriter<>();
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write(summarizeHeader.header());
			}
		});

		writer.setResource(new FileSystemResource(outputDir + DateUtils.localDateTimeToyyyyMMddHHmmss(LocalDateTime.now()) + outputType));
		writer.setAppendAllowed(false);
		writer.setLineAggregator((summarize) -> {
			return summarize.getCustomersQuantity() + delimiter + summarize.getSellersQuantity() + delimiter
					+ summarize.getBiggestSale().getSaleId() + delimiter + summarize.getWorstSeller().getSalesManName();
		});
		writer.open(new ExecutionContext());
		writer.write(items);
		writer.close();
	}
}
