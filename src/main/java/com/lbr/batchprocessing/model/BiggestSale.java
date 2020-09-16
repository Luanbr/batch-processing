package com.lbr.batchprocessing.model;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
public class BiggestSale {

	private Long saleId;

	private Double total = 0.0;

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "BiggestSale [saleId=" + saleId + ", total=" + total + "]";
	}

}
