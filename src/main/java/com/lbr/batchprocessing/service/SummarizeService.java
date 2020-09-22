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

import com.lbr.batchprocessing.exceptions.UpdateSalesmanTotalSalesMapException;
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
			summarize.setCustomersQuantity(0L);
			summarize.setSalespeopleQuantity(0L);
			summarize.getBiggestSale().setSaleId(null);
			summarize.getBiggestSale().setTotal(0.0);
			summarize.getWorstSeller().setSalesmanName(null);
			summarize.getWorstSeller().setTotal(0.0);
			summarize.getSalesmanTotalSalesMap().clear();
		} catch (Exception e) {
			logger.error("Error during cleaning summary", e);
			throw e;
		}
	}
	
	public void addCustomersCount() {
		Long customersQuantity = summarize.getCustomersQuantity() + 1;
		summarize.setCustomersQuantity(customersQuantity);
	}
	
	public void addSalespeopleCount() {
		Long salespeopleQuantity = summarize.getSalespeopleQuantity() + 1;
		summarize.setSalespeopleQuantity(salespeopleQuantity);
	}

	public void updateBiggestSale(Long saleId, Double totalSale) {
		try {
			Double actualBiggestTotalSale = summarize.getBiggestSale().getTotal(); 
			
			if(actualBiggestTotalSale < totalSale) {
				summarize.getBiggestSale().setSaleId(saleId);
				summarize.getBiggestSale().setTotal(totalSale);
			}				
		} catch (Exception e) {
			logger.error("Error during update biggest sale ", e);
			throw e;
		}
	}

	public void updateSalesmanTotalSalesMap(String salesmanName, Double totalSale) throws Exception {
		try {
			if(Objects.isNull(salesmanName) || Objects.isNull(totalSale))
				throw new UpdateSalesmanTotalSalesMapException("Salesman name or total sale cannot be null!");
			
			Double actualTotalSale = summarize.getSalesmanTotalSalesMap().get(salesmanName);
			
			if(Objects.nonNull(actualTotalSale)) {
				actualTotalSale = actualTotalSale + totalSale;
				summarize.getSalesmanTotalSalesMap().replace(salesmanName, actualTotalSale);
			} else {
				summarize.getSalesmanTotalSalesMap().put(salesmanName, totalSale);
			}
		} catch (Exception e) {
			logger.error("Error during update salesman total sales map ", e);
			throw e;
		}
	}
	
	public void updateWorstSeller() {
		try {
			Entry<String, Double> worstSeller = summarize.getSalesmanTotalSalesMap()
					.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
					.collect(Collectors.toList()).get(0);
			if(Objects.nonNull(worstSeller)) {
				summarize.getWorstSeller().setSalesmanName(worstSeller.getKey());
				summarize.getWorstSeller().setTotal(worstSeller.getValue());
			}
		} catch (Exception e) {
			logger.error("Error during update worst seller ", e);
			throw e;
		}
	}
}
