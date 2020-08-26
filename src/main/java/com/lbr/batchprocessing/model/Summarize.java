package com.lbr.batchprocessing.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class Summarize {
	private static final int _0 = 0;
	private final Long customersQuantity;
	private final Long sellersQuantity;
	private final BiggestSale biggestSale;
	private final WorstSeller worstSeller;

	@Value("${io.output.delimiter}")
	private String delimiter;
	@Value("${io.output.header.customersQuantity}")
	private String customersQuantityHeader;
	@Value("${io.output.header.sellersQuantity}")
	private String sellersQuantityHeader;
	@Value("${io.output.header.biggestSale}")
	private String biggestSaleHeader;
	@Value("${io.output.header.worstSeller}")
	private String worstSellerHeader;

	public Summarize() {
		this.customersQuantity = 0L;
		this.sellersQuantity = 0L;
		this.biggestSale = null;
		this.worstSeller = null;
	}

	public Summarize(Long customersQuantity, Long sellersQuantity, BiggestSale biggestSale, WorstSeller worstSeller) {
		super();
		this.customersQuantity = customersQuantity;
		this.sellersQuantity = sellersQuantity;
		this.biggestSale = biggestSale;
		this.worstSeller = worstSeller;
	}	
	

	public Long getCustomersQuantity() {
		return customersQuantity;
	}

	public Long getSellersQuantity() {
		return sellersQuantity;
	}

	public BiggestSale getBiggestSale() {
		return biggestSale;
	}

	public WorstSeller getWorstSeller() {
		return worstSeller;
	}
	
	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getCustomersQuantityHeader() {
		return customersQuantityHeader;
	}

	public void setCustomersQuantityHeader(String customersQuantityHeader) {
		this.customersQuantityHeader = customersQuantityHeader;
	}

	public String getSellersQuantityHeader() {
		return sellersQuantityHeader;
	}

	public void setSellersQuantityHeader(String sellersQuantityHeader) {
		this.sellersQuantityHeader = sellersQuantityHeader;
	}

	public String getBiggestSaleHeader() {
		return biggestSaleHeader;
	}

	public void setBiggestSaleHeader(String biggestSaleHeader) {
		this.biggestSaleHeader = biggestSaleHeader;
	}

	public String getWorstSellerHeader() {
		return worstSellerHeader;
	}

	public void setWorstSellerHeader(String worstSellerHeader) {
		this.worstSellerHeader = worstSellerHeader;
	}

	public boolean isEmpty() {
		return (this.customersQuantity == _0 && this.sellersQuantity == _0 && this.biggestSale == null
				&& this.worstSeller == null) ? true : false;
	}

	public String header() {
		return String.join(delimiter, customersQuantityHeader, sellersQuantityHeader, biggestSaleHeader,
				worstSellerHeader);
	}

	@Override
	public String toString() {
		return "Summarize [customersQuantity=" + customersQuantity + ", sellersQuantity=" + sellersQuantity
				+ ", biggestSale=" + biggestSale + ", worstSeller=" + worstSeller + ", delimiter=" + delimiter
				+ ", customersQuantityHeader=" + customersQuantityHeader + ", sellersQuantityHeader="
				+ sellersQuantityHeader + ", biggestSaleHeader=" + biggestSaleHeader + ", worstSellerHeader="
				+ worstSellerHeader + "]";
	}
}
