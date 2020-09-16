package com.lbr.batchprocessing.model;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
public class WorstSalesman {
	private String salesmanName;
	private Double total;

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "WorstSalesman [salesmanName=" + salesmanName + ", total=" + total + "]";
	}
}
