package com.lbr.batchprocessing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(SaleService.class);
	
	@Autowired
	private SummarizeService summarizeService;
	
	@Override
	public void process(Object item) {
		try {
			Sale sale = (Sale) item;
			Double totalSale = sale.getItems()
					.stream()
					.mapToDouble(saleItem -> (saleItem.getQuantity() * saleItem.getPrice()))
					.sum();
			
			summarizeService.updateBiggestSale(sale.getSaleId(), totalSale);
			summarizeService.updateSalesmanTotalSalesMap(sale.getSalesmanName(), totalSale);
		} catch (Exception e) {
			logger.error("Error during process sale. ", e);
		}
	}
}
