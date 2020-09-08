package com.lbr.batchprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.model.WorstSalesman;
import com.lbr.batchprocessing.repository.SaleCustomRepository;
import com.lbr.batchprocessing.repository.SaleRepository;

@Service
public class SaleService implements IItemService {

	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private SaleCustomRepository saleCustomRepository;
	
	public BiggestSale findBiggestSale() {
		return saleCustomRepository.findBiggestSale();
	}
	
	public WorstSalesman findWorstSeller() {
		return saleCustomRepository.findWorstSalesman();
	}

	public void deleteAll() {
		saleRepository.deleteAll();
	}

	@Override
	public void save(final Object item) {
		saleRepository.save((Sale) item);
	}
}
