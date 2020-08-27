package com.lbr.batchprocessing.batch.writers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.service.SaleService;

@Component
public class SaleMongoWriter implements ItemWriter<Sale> {

	@Autowired
	private SaleService salesService;

	@Override
	public void write(List<? extends Sale> items) throws Exception {
		salesService.saveAll(items);
	}

	public void write(Sale item) throws Exception {
		List<Sale> sales = new ArrayList<>();
		sales.add(item);
		write(sales);
	}

}
