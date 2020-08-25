package com.lbr.batchprocessing.model;

public class WorstSeller {
	private String salesManName;
	private long total;

	public String getSalesManName() {
		return salesManName;
	}

	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "WorstSeller [salesManName=" + salesManName + ", total=" + total + "]";
	}
}
