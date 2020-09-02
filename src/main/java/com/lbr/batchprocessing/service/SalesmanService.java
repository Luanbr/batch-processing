package com.lbr.batchprocessing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.Salesman;
import com.lbr.batchprocessing.repository.SalesmanRepository;
import com.lbr.batchprocessing.repository.SalesmanCustomRepository;

@Service
public class SalesmanService implements IService {

	@Autowired
	private SalesmanRepository repository;

	@Autowired
	private SalesmanCustomRepository customRepository;
	
	public void saveAll(List<? extends Salesman> sellers) {
		repository.saveAll(sellers);
	}
	
	public Long countSalesManDistinctByCnpj() {
		return customRepository.countDistinctByCpf();
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void save(final Object item) {
		repository.save((Salesman) item);
	}
}
