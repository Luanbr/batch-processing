package com.lbr.batchprocessing.batch.mappers;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Item;
import com.lbr.batchprocessing.model.Sale;

@Component
public class SaleMapper implements FieldSetMapper<Sale> {

	@Override
	public Sale mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null) {
			return null;
		}
		Sale vendas = new Sale();
		vendas.setSaleId(fieldSet.readLong("salesId"));
		vendas.setItems(
				Arrays.stream(fieldSet.readString("items").replaceAll("\\[(.*)\\]", "$1").split(",")).map(item -> {
					String[] value = item.split("-");
					Long id = Long.valueOf(value[0]);
					Long quantity = Long.valueOf(value[1]);
					Double price = Double.valueOf(value[2]);
					return new Item(id, quantity, price);
				}).collect(Collectors.toList()));
		vendas.setSalesmanName(fieldSet.readString("salesManName"));
		return vendas;
	}
}
