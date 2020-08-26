package com.lbr.batchprocessing.batch.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Customer;

@Component
public class CustomerMapper implements FieldSetMapper<Customer> {

	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setId(fieldSet.readString("id"));
		customer.setCnpj(fieldSet.readLong("cnpj"));
		customer.setName(fieldSet.readString("name"));
		customer.setBusinessArea(fieldSet.readString("businessArea"));
		return customer;
	}
}