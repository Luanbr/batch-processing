package com.lbr.batchprocessing.model;

public class BiggestSale {

	private Long saleId;

	private long total;

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "BiggestSale [saleId=" + saleId + ", total=" + total + "]";
	}

}
