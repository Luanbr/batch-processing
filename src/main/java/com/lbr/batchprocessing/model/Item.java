package com.lbr.batchprocessing.model;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
public class Item {
	private Long id;
	private Long quantity;
	private Double price;

	public Item() {
	}

	public Item(Long id, Long quantity, Double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
