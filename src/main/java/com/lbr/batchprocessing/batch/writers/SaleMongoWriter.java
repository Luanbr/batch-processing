package com.lbr.batchprocessing.batch.writers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.service.SaleService;

@Component
public class SaleMongoWriter implements ItemWriter<Sale>, LineWriter<Sale> {

	@Autowired
	private SaleService salesService;

	@Override
	public void write(List<? extends Sale> items) throws Exception {
		salesService.saveAll(items);
	}

	@Override
	public void write(Object item) throws Exception {
		List<Sale> sales = new ArrayList<>();
		sales.add((Sale) item);
		write(sales);
	}

}
