package com.lbr.batchprocessing.batch.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Salesman;

import java.util.Objects;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
public class SalesmanMapper implements FieldSetMapper<Salesman> {
	private static final Logger logger = LoggerFactory.getLogger(SalesmanMapper.class);

	@Override
	public Salesman mapFieldSet(FieldSet fieldSet) throws BindException {
		try {
			if (Objects.isNull(fieldSet)) {
				return null;
			}
			Salesman salesMan = new Salesman();
			salesMan.setCpf(fieldSet.readLong("cpf"));
			salesMan.setName(fieldSet.readString("name"));
			salesMan.setSalary(fieldSet.readDouble("salary"));
			return salesMan;
		} catch (Exception e) {
			logger.error("Error during mapping data obtained from a FieldSet into an object Salesman!", e);
			throw e;
		}
	}

}
