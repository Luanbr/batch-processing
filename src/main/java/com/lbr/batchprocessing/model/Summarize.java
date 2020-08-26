package com.lbr.batchprocessing.model;

/***
 * 
 * @author luan.barbosa.ramalho
 *
 */
public class Summarize {
	private static final int _0 = 0;
	public static final String HEADER = "QUANTIDADE_CLIENTES;QUANTIDADE_VENDEDORES;ID_VENDA_MAIOR_VALOR;PIOR_VENDEDOR";
	private final Long customersQuantity;
	private final Long sellersQuantity;
	private final BiggestSale biggestSale;
	private final WorstSeller worstSeller;

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
	
	public boolean isEmpty() {
		return (this.customersQuantity == _0 
			&& this.sellersQuantity == _0 
			&& this.biggestSale == null
			&& this.worstSeller == null) ? true : false;
	}

	@Override
	public String toString() {
		return "Summarize [customersQuantity=" + customersQuantity + ", sellersQuantity=" + sellersQuantity
				+ ", biggestSale=" + biggestSale + ", worstSeller=" + worstSeller + "]";
	}

}
