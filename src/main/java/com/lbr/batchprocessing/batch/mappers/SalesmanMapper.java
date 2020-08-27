package com.lbr.batchprocessing.batch.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Salesman;

@Component
public class SalesmanMapper implements FieldSetMapper<Salesman> {

	@Override
	public Salesman mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null) {
			return null;
		}
		Salesman salesMan = new Salesman();
		salesMan.setCpf(fieldSet.readLong("cpf"));
		salesMan.setName(fieldSet.readString("name"));
		salesMan.setSalary(fieldSet.readDouble("salary"));
		return salesMan;
	}

}
