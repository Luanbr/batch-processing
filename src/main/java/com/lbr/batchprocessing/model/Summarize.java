package com.lbr.batchprocessing.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/***
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Summarize {
	private static final Long ZERO = 0L;
	private Long customersQuantity;
	private Long sellersQuantity;
	private final BiggestSale biggestSale;
	private final WorstSalesman worstSeller;
	private final Map<String, Double> salesmanTotalSalesMap;

	public Summarize() {
		this.customersQuantity = ZERO;
		this.sellersQuantity = ZERO;
		this.biggestSale = new BiggestSale();
		this.worstSeller = new WorstSalesman();
		this.salesmanTotalSalesMap = new HashMap<String, Double>();
	}

	public Long getCustomersQuantity() {
		return customersQuantity;
	}

	public Long getSellersQuantity() {
		return sellersQuantity;
	}

	public BiggestSale getBiggestSale() {
		return biggestSale;
	}

	public WorstSalesman getWorstSeller() {
		return worstSeller;
	}

	public Map<String, Double> getSalesmanTotalSalesMap() {
		return salesmanTotalSalesMap;
	}

	public void addCustomersCount() {
		this.customersQuantity++;
	}

	public void addSalespeopleCount() {
		this.sellersQuantity++;
	}

	public void clean() {
		this.customersQuantity = ZERO;
		this.sellersQuantity = ZERO;
		this.biggestSale.setSaleId(null);
		this.biggestSale.setTotal(0.0);
		this.worstSeller.setSalesmanName(null);
		this.worstSeller.setTotal(0.0);
		this.salesmanTotalSalesMap.clear();
	}

	public boolean isEmpty() {
		return (this.customersQuantity == ZERO && this.sellersQuantity == ZERO && this.biggestSale.getSaleId() == null
				&& this.worstSeller.getSalesmanName() == null);
	}
}
