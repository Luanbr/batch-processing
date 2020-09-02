package com.lbr.batchprocessing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.repository.CustomerCustomRepository;
import com.lbr.batchprocessing.repository.CustomerRepository;

@Service
public class CustomerService implements IService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CustomerCustomRepository customRepository;
	
	public void saveAll(List<? extends Customer> customers) {
		repository.saveAll(customers);
	}
	
	public Long countCustomerDistinctByCnpj() {
		return customRepository.countDistinctByCnpj();
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void save(final Object item) {
		repository.save((Customer) item);
	}
}
