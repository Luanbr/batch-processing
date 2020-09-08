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

import com.lbr.batchprocessing.model.Customer;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerMapperTest {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Test
	public void whenFieldSetIsNull_thenReturnNull() throws BindException {
		FieldSet fieldSet = null;
		Customer mappedCustomer = customerMapper.mapFieldSet(fieldSet);
		assertThat(mappedCustomer).isNull();
	}

	@Test
	public void whenFieldSetHasBindInvalid_thenReturnException() throws BindException {
		FieldSet fieldSetMock = mock(FieldSet.class); 
		when(fieldSetMock.readLong("cnpj")).thenThrow(new IllegalArgumentException());

		assertThatThrownBy(() -> {
			customerMapper.mapFieldSet(fieldSetMock);
		}).isInstanceOf(Exception.class);
	}
}
