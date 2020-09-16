package com.lbr.batchprocessing.batch.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Customer;

import java.util.Objects;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class CustomerMapper implements FieldSetMapper<Customer> {
	private static final Logger logger = LoggerFactory.getLogger(CustomerMapper.class);

	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
		try {
			if (Objects.isNull(fieldSet)) {
				return null;
			}
			Customer customer = new Customer();
			customer.setCnpj(fieldSet.readLong("cnpj"));
			customer.setName(fieldSet.readString("name"));
			customer.setBusinessArea(fieldSet.readString("businessArea"));
			return customer;			
		} catch (Exception e) {
			logger.error("Error during mapping data obtained from a FieldSet into an object Customer!", e);
			throw e;
		}
	}
}
