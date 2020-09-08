package com.lbr.batchprocessing.batch.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindException;

import com.lbr.batchprocessing.model.Salesman;


@SpringBootTest
@ActiveProfiles("test")
public class SalesmanMapperTest {
	@Autowired
	private SalesmanMapper salesmanMapper;
	
	@Test
	public void whenFieldSetIsNull_thenReturnNull() throws BindException {
		FieldSet fieldSet = null;
		Salesman mappedSalesman = salesmanMapper.mapFieldSet(fieldSet);
		assertThat(mappedSalesman).isNull();
	}

	@Test
	public void whenFieldSetHasBindInvalid_thenReturnException() {
		FieldSet fieldSetMock = mock(FieldSet.class); 
		when(fieldSetMock.readLong("cpf")).thenThrow(new IllegalArgumentException());

		assertThatThrownBy(() -> {
			salesmanMapper.mapFieldSet(fieldSetMock);
		}).isInstanceOf(Exception.class);
	}
}
