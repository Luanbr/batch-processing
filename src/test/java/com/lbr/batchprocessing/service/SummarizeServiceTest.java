package com.lbr.batchprocessing.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SummarizeServiceTest {

	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private SummarizeService summarizeService;
	
//	@Test
//	public void whenErrorOccurDuringCreate_thenReturnException() {
//		when(customerService.countDistinctByCnpj()).thenThrow(new NullPointerException());
//		
//		assertThatThrownBy(() -> {
//			summarizeService.create();
//		}).isInstanceOf(Exception.class);
//	}
//
//	@Test
//	public void whenErrorOccurDuringClean_thenReturnException() {		
//		doThrow(new NullPointerException()).when(customerService).deleteAll();
//		
//		assertThatThrownBy(() -> {
//			summarizeService.clean();
//		}).isInstanceOf(Exception.class);
//	}
}
