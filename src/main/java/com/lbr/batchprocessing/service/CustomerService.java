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
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerCustomRepository customerCustomRepository;
	
	public void saveAll(List<? extends Customer> customers) {
		customerRepository.saveAll(customers);
	}
	
	public Long countDistinctByCnpj() {
		return customerCustomRepository.countDistinctByCnpj();
	}

	public void deleteAll() {
		customerRepository.deleteAll();
	}

	@Override
	public void save(final Object item) {
		customerRepository.save((Customer) item);
	}
}
