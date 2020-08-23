package com.lbr.batchprocessing.model;

import java.util.List;

public class Sales {
	private String id;
	private Long saleId;
	private List<Item> items;
	private String salesManName;

	public Sales() {
	}

	public Sales(String id, Long saleId, List<Item> items, String salesManName) {
		super();
		this.id = id;
		this.saleId = saleId;
		this.items = items;
		this.salesManName = salesManName;
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

	public String getSalesManName() {
		return salesManName;
	}

	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vendas [id=" + id + ", saleId=" + saleId + ", items=" + items + ", salesManName=" + salesManName + "]";
	}
}
