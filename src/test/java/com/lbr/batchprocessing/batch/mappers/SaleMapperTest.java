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

import com.lbr.batchprocessing.model.Sale;

@SpringBootTest
@ActiveProfiles("test")
public class SaleMapperTest {
	@Autowired
	private SaleMapper saleMapper;
	
	@Test
	public void whenFieldSetIsNull_thenReturnNull() throws BindException {
		FieldSet fieldSet = null;
		Sale mappedSale = saleMapper.mapFieldSet(fieldSet);
		assertThat(mappedSale).isNull();
	}

	@Test
	public void whenFieldSetHasBindInvalid_thenReturnException() throws BindException {
		FieldSet fieldSetMock = mock(FieldSet.class); 
		when(fieldSetMock.readLong("salesId")).thenThrow(new IllegalArgumentException());

		assertThatThrownBy(() -> {
			saleMapper.mapFieldSet(fieldSetMock);
		}).isInstanceOf(Exception.class);
	}
}
