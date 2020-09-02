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
	private SaleRepository repository;
	@Autowired
	private SaleCustomRepository customRepository;

	public void saveAll(List<? extends Sale> sales) {
		repository.saveAll(sales);
	}
	
	public BiggestSale findBiggestSale() {
		return customRepository.findBiggestSale();
	}
	
	public WorstSalesman findWorstSeller() {
		return customRepository.findWorstSalesman();
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void save(final Object item) {
		repository.save((Sale) item);
	}
}
