package com.lbr.batchprocessing.model;

import java.util.List;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
public class Sale {
	private Long saleId;
	private List<Item> items;
	private String salesmanName;

	public Sale() {
	}

	public Sale(Long saleId, List<Item> items, String salesmanName) {
		super();
		this.saleId = saleId;
		this.items = items;
		this.salesmanName = salesmanName;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	@Override
	public String toString() {
		return "Sale [saleId=" + saleId + ", items=" + items + ", salesmanName=" + salesmanName + "]";
	}
}
