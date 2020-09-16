package com.lbr.batchprocessing.service;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.Summarize;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Service
public class SummarizeService {
	private static final Logger logger = LoggerFactory.getLogger(SummarizeService.class);

	@Autowired
	private Summarize summarize;

	public void clean() {
		try {
			summarize.clean();
		} catch (Exception e) {
			logger.error("Error during cleaning summary", e);
			throw e;
		}
	}
	
	public void addCustomersCount() {
		summarize.addCustomersCount();
	}
	
	public void addSalespeopleCount() {
		summarize.addSalespeopleCount();
	}

	public void updateBiggestSale(Long saleId, Double totalSale) {
		Double actualBiggestTotalSale = summarize.getBiggestSale().getTotal(); 
		
		if(actualBiggestTotalSale < totalSale) {
			summarize.getBiggestSale().setSaleId(saleId);
			summarize.getBiggestSale().setTotal(totalSale);
		}		
	}

	public void updateSalesmanTotalSalesMap(String salesmanName, Double totalSale) {
		Double actualTotalSale = summarize.getSalesmanTotalSalesMap().get(salesmanName);
		
		if(Objects.nonNull(actualTotalSale)) {
			actualTotalSale = actualTotalSale + totalSale;
			summarize.getSalesmanTotalSalesMap().replace(salesmanName, actualTotalSale);
		} else {
			summarize.getSalesmanTotalSalesMap().put(salesmanName, totalSale);
		}
	}
	
	public void updateWorstSeller() {
		Entry<String, Double> worstSeller = summarize.getSalesmanTotalSalesMap()
				.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
				.collect(Collectors.toList()).get(0);
		if(Objects.nonNull(worstSeller)) {
			summarize.getWorstSeller().setSalesmanName(worstSeller.getKey());
			summarize.getWorstSeller().setTotal(worstSeller.getValue());
		}
	}
}
