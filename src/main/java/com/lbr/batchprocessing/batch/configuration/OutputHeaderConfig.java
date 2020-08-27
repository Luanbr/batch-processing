package com.lbr.batchprocessing.batch.configuration;

public class OutputHeaderConfig {
	private String customersQuantity;
	private String sellersQuantity;
	private String biggestSale;
	private String worstSeller;

	public String getCustomersQuantity() {
		return customersQuantity;
	}

	public void setCustomersQuantity(String customersQuantity) {
		this.customersQuantity = customersQuantity;
	}

	public String getSellersQuantity() {
		return sellersQuantity;
	}

	public void setSellersQuantity(String sellersQuantity) {
		this.sellersQuantity = sellersQuantity;
	}

	public String getBiggestSale() {
		return biggestSale;
	}

	public void setBiggestSale(String biggestSale) {
		this.biggestSale = biggestSale;
	}

	public String getWorstSeller() {
		return worstSeller;
	}

	public void setWorstSeller(String worstSeller) {
		this.worstSeller = worstSeller;
	}

	@Override
	public String toString() {
		return "OutputHeaderConfig [customersQuantity=" + customersQuantity + ", sellersQuantity=" + sellersQuantity
				+ ", biggestSale=" + biggestSale + ", worstSeller=" + worstSeller + "]";
	}

}
