package com.lbr.batchprocessing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.Salesman;
import com.lbr.batchprocessing.repository.SalesmanRepository;
import com.lbr.batchprocessing.repository.SalesmanCustomRepository;

@Service
public class SalesmanService implements ILine {

	@Autowired
	private SalesmanRepository salesmanRepository;

	@Autowired
	private SalesmanCustomRepository salesmanCustomRepository;
	
	public void saveAll(List<? extends Salesman> sellers) {
		salesmanRepository.saveAll(sellers);
	}
	
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
