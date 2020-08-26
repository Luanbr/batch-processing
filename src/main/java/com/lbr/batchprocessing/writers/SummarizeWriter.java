package com.lbr.batchprocessing.writers;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Summarize;

@Component
public class SummarizeWriter implements ItemWriter<Summarize> {

	private static final Logger logger = LoggerFactory.getLogger(SummarizeWriter.class);

	@Value("${output.dir}")
	private String outputDir;

	@Override
	public void write(List<? extends Summarize> items) throws Exception {
		FlatFileItemWriter<Summarize> writer = new FlatFileItemWriter<>();
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write(Summarize.HEADER);
			}
		});

		writer.setResource(new FileSystemResource(outputDir));
		writer.setAppendAllowed(false);
		writer.setLineAggregator((summarize) -> {
			return summarize.getCustomersQuantity() + ";" + summarize.getSellersQuantity() + ";"
					+ summarize.getBiggestSale().getSaleId() + ";" + summarize.getWorstSeller().getSalesManName();
		});
		writer.open(new ExecutionContext());
		writer.write(items);
	}
}
