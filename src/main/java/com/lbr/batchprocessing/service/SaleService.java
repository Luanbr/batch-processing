package com.lbr.batchprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.Sale;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Service
public class SaleService implements ILineService {

	@Autowired
	private SummarizeService summarizeService;
	
	@Override
	public void process(Object item) {
		Sale sale = (Sale) item;
		Double totalSale = sale.getItems()
				.stream()
				.mapToDouble(saleItem -> (saleItem.getQuantity() * saleItem.getPrice()))
				.sum();
		
		summarizeService.updateBiggestSale(sale.getSaleId(), totalSale);
		summarizeService.updateSalesmanTotalSalesMap(sale.getSalesmanName(), totalSale);
	}
}
