package com.lbr.batchprocessing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.model.WorstSalesman;
import com.lbr.batchprocessing.repository.SaleRepository;
import com.lbr.batchprocessing.repository.SaleCustomRepository;

@Service
public class SaleService implements IService {

	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private SaleCustomRepository saleCustomRepository;

	public void saveAll(List<? extends Sale> sales) {
		saleRepository.saveAll(sales);
	}
	
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
