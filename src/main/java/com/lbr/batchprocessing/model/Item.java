package com.lbr.batchprocessing.model;

public class Item {
	private Long itemId;
	private Long itemQuantity;
	private Double itemPrice;

	public Item() {
	}

	public Item(Long itemId, Long itemQuantity, Double itemPrice) {
		super();
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice + "]";
	}

}
