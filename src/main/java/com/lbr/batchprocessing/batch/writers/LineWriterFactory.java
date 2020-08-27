package com.lbr.batchprocessing.batch.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.model.Salesman;

@Component
public class LineWriterFactory {
	@Autowired
	private CustomerMongoWriter customerWriter;
	@Autowired
	private SalesmanMongoWriter salesManWriter;
	@Autowired
	private SaleMongoWriter salesWriter;

	public LineWriter<?> getInstance(Object item) {
		if (item instanceof Customer) {
			return (LineWriter<Customer>) customerWriter;
		} else if (item instanceof Salesman) {
			return (LineWriter<Salesman>) salesManWriter;
		} else if (item instanceof Sale) {
			return (LineWriter<Sale>) salesWriter;
		}
		return null;
	}
}
