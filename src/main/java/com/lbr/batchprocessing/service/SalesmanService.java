package com.lbr.batchprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.Salesman;
import com.lbr.batchprocessing.repository.SalesmanCustomRepository;
import com.lbr.batchprocessing.repository.SalesmanRepository;

@Service
public class SalesmanService implements IItemService {

	@Autowired
	private SalesmanRepository salesmanRepository;

	@Autowired
	private SalesmanCustomRepository salesmanCustomRepository;
		
	public Long countDistinctByCnpj() {
		return salesmanCustomRepository.countDistinctByCpf();
	}

	public void deleteAll() {
		salesmanRepository.deleteAll();
	}

	@Override
	public void save(final Object item) {
		salesmanRepository.save((Salesman) item);
	}
}
