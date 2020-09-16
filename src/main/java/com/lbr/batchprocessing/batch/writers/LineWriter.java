package com.lbr.batchprocessing.batch.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.service.ILineService;
import com.lbr.batchprocessing.service.ServiceFactory;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class LineWriter implements ItemWriter<Object> {
	private static final Logger logger = LoggerFactory.getLogger(LineWriter.class);

	@Autowired
	private ServiceFactory serviceFactory;

	@Override
	public void write(List<?> items) {
		logger.info("Preparing " + items.size() + " items to summarize!!");
		items.forEach(item -> {
			try {
				final ILineService service = serviceFactory.create(item);
				service.process(item);
			} catch (Exception e) {
				logger.error("Error while prepare items to summarize ", e);
			}
		});
		logger.info("Preparing items to summarize ended!!!");
	}
}
