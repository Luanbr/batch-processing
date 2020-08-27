package com.lbr.batchprocessing.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.batch.configuration.OutputFileConfigProperties;

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
	private final WorstSalesman worstSeller;

	@Autowired
	private OutputFileConfigProperties configProperties;

	public Summarize() {
		this.customersQuantity = 0L;
		this.sellersQuantity = 0L;
		this.biggestSale = null;
		this.worstSeller = null;
	}

	public Summarize(Long customersQuantity, Long sellersQuantity, BiggestSale biggestSale, WorstSalesman worstSeller) {
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

	public WorstSalesman getWorstSeller() {
		return worstSeller;
	}

	public boolean isEmpty() {
		return (this.customersQuantity == _0 && this.sellersQuantity == _0 && this.biggestSale == null
				&& this.worstSeller == null) ? true : false;
	}

	public String header() {
		return String.join(configProperties.getDelimiter(), configProperties.getHeader().getCustomersQuantity(),
				configProperties.getHeader().getSellersQuantity(), configProperties.getHeader().getBiggestSale(),
				configProperties.getHeader().getWorstSeller());
	}

}
