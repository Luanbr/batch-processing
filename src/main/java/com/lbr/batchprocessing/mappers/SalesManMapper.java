package com.lbr.batchprocessing.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.SalesMan;

@Component
public class SalesManMapper implements FieldSetMapper<SalesMan> {

	@Override
	public SalesMan mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null) {
			return null;
		}
		SalesMan salesMan = new SalesMan();
		salesMan.setId(fieldSet.readString("id"));
		salesMan.setCpf(fieldSet.readLong("cpf"));
		salesMan.setName(fieldSet.readString("name"));
		salesMan.setSalary(fieldSet.readDouble("salary"));
		return salesMan;
	}

}
