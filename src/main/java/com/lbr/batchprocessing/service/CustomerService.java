package com.lbr.batchprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Service
public class CustomerService implements ILineService {
	
	@Autowired
	private SummarizeService summarizeService;
		
	@Override
	public void process(final Object item) {
		summarizeService.addCustomersCount();
	}
}
