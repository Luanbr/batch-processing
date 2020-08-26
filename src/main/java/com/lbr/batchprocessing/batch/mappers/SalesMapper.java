package com.lbr.batchprocessing.batch.mappers;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Item;
import com.lbr.batchprocessing.model.Sales;

@Component
public class SalesMapper implements FieldSetMapper<Sales> {

	@Override
	public Sales mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null) {
			return null;
		}
		Sales vendas = new Sales();
		vendas.setId(fieldSet.readString("id"));
		vendas.setSaleId(fieldSet.readLong("salesId"));
		vendas.setItems(
				Arrays.stream(fieldSet.readString("items").replace("[", "").replace("]", "").split(",")).map(item -> {
					String[] value = item.split("-");
					return new Item(Long.valueOf(value[0]), Long.valueOf(value[1]), Double.valueOf(value[2]));
				}).collect(Collectors.toList()));
		vendas.setSalesManName(fieldSet.readString("salesManName"));
		return vendas;
	}
}
