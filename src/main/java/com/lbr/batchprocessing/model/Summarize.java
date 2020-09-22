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
	private Long salespeopleQuantity;
	private final BiggestSale biggestSale;
	private final WorstSalesman worstSeller;
	private final Map<String, Double> salesmanTotalSalesMap;

	public Summarize() {
		this.customersQuantity = ZERO;
		this.salespeopleQuantity = ZERO;
		this.biggestSale = new BiggestSale();
		this.worstSeller = new WorstSalesman();
		this.salesmanTotalSalesMap = new HashMap<String, Double>();
	}

	public Long getCustomersQuantity() {
		return customersQuantity;
	}

	public Long getSalespeopleQuantity() {
		return salespeopleQuantity;
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
	
	public void setCustomersQuantity(Long customersQuantity) {
		this.customersQuantity = customersQuantity;
	}

	public void setSalespeopleQuantity(Long salespeopleQuantity) {
		this.salespeopleQuantity = salespeopleQuantity;
	}

	public boolean isEmpty() {
		return (this.customersQuantity == ZERO && this.salespeopleQuantity == ZERO && this.biggestSale.getSaleId() == null
				&& this.worstSeller.getSalesmanName() == null && this.salesmanTotalSalesMap.size() == ZERO);
	}

	@Override
	public String toString() {
		return "Summarize [customersQuantity=" + customersQuantity + ", salespeopleQuantity=" + salespeopleQuantity
				+ ", biggestSale=" + biggestSale + ", worstSeller=" + worstSeller + ", salesmanTotalSalesMap="
				+ salesmanTotalSalesMap + "]";
	}
}
