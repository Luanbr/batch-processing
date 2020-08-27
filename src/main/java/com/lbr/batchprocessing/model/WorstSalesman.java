package com.lbr.batchprocessing.model;

public class WorstSalesman {
	private String salesmanName;
	private long total;

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "WorstSalesman [salesmanName=" + salesmanName + ", total=" + total + "]";
	}
}
