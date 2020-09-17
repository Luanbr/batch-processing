package com.lbr.batchprocessing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.lbr.batchprocessing.exceptions.UpdateSalesmanTotalSalesMapException;
import com.lbr.batchprocessing.model.Summarize;

@SpringBootTest
@ActiveProfiles("test")
public class SummarizeServiceTest {

	@Autowired
	private Summarize summarize;

	@Autowired
	private SummarizeService summarizeService;

	@Test
	public void whenClean_thenCleanCorrectly() {
		String salesmanName = "Luan";
		Double totalSale = 15000.0;
		summarize.addCustomersCount();
		summarize.addSalespeopleCount();
		summarize.getBiggestSale().setSaleId(123L);
		summarize.getBiggestSale().setTotal(12.50);
		summarize.getWorstSeller().setSalesmanName(salesmanName);
		summarize.getWorstSeller().setTotal(totalSale);
		summarize.getSalesmanTotalSalesMap().put(salesmanName, totalSale);

		summarizeService.clean();

		assertThat(summarize.isEmpty()).isTrue();

		assertThat(summarize).matches(
				summary -> summary.getCustomersQuantity() == 0 && summary.getSellersQuantity() == 0
						&& summary.getBiggestSale().getSaleId() == null && summary.getBiggestSale().getTotal() == 0
						&& summary.getWorstSeller().getSalesmanName() == null
						&& summary.getWorstSeller().getTotal() == 0 && summary.getSalesmanTotalSalesMap().size() == 0,
				summarize.toString());
	}

	@Test
	public void whenUpdateBiggestSaleCalled_thenUpdateCorrectly() {
		Long expectedSaleId = 228882L;
		Double expectedTotalSale = 33000.99;

		summarizeService.updateBiggestSale(expectedSaleId, expectedTotalSale);

		assertThat(summarize.getBiggestSale().getSaleId()).isEqualTo(expectedSaleId);
		assertThat(summarize.getBiggestSale().getTotal()).isEqualTo(expectedTotalSale);
	}

	@Test
	public void whenErrorOccurDuringUpdateBiggestSale_thenReturnException() {
		Long expectedSaleId = 228882L;
		Double expectedTotalSale = null;

		assertThatThrownBy(() -> {
			summarizeService.updateBiggestSale(expectedSaleId, expectedTotalSale);
		}).isInstanceOf(Exception.class);
	}

	@Test
	public void whenUpdateSalesmanTotalSalesMapCalledAndKeyExist_thenSumTotalSaleCorrectly() throws Exception {
		String salesmanName = "Luan";
		Double actualTotalSale = 2777.82;
		Double newTotalSale = 28899.50;
		Double expectedTotalSale = 31677.32;
		summarize.getSalesmanTotalSalesMap().put(salesmanName, actualTotalSale);

		summarizeService.updateSalesmanTotalSalesMap(salesmanName, newTotalSale);

		assertThat(summarize.getSalesmanTotalSalesMap().get(salesmanName)).isEqualTo(expectedTotalSale);
	}

	@Test
	public void whenUpdateSalesmanTotalSalesMapCalledAndKeyNotExist_thenAddCorrectly() throws Exception {
		String salesmanName = "Luan";
		Double expectedTotalSale = 31600.45;

		summarizeService.updateSalesmanTotalSalesMap(salesmanName, expectedTotalSale);

		assertThat(summarize.getSalesmanTotalSalesMap().get(salesmanName)).isEqualTo(expectedTotalSale);
	}

	@Test
	public void whenUpdateSalesmanTotalSalesMapCalledAndSalesmanNameIsNull_thenReturnUpdateSalesmanTotalSalesMapException()
			throws Exception {
		String salesmanName = null;
		Double expectedTotalSale = 31600.45;

		assertThatThrownBy(() -> {
			summarizeService.updateSalesmanTotalSalesMap(salesmanName, expectedTotalSale);
		}).isInstanceOf(UpdateSalesmanTotalSalesMapException.class)
		.hasMessage("Salesman name or total sale cannot be null!");
	}

	@Test
	public void whenUpdateSalesmanTotalSalesMapCalledAndTotalSaleIsNull_thenReturnUpdateSalesmanTotalSalesMapException()
			throws Exception {
		String salesmanName = "Luan";
		Double expectedTotalSale = null;
		
		assertThatThrownBy(() -> {
			summarizeService.updateSalesmanTotalSalesMap(salesmanName, expectedTotalSale);
		}).isInstanceOf(UpdateSalesmanTotalSalesMapException.class)
		.hasMessage("Salesman name or total sale cannot be null!");
	}
	
	@Test
	public void whenUpdateWorstSellerCalled_thenUpdateWorstSellerCorrectly() throws Exception {
		String expectedWorstSellerSalesmanName = "Luan";
		Double expectedWorstSellerTotalSales = 31677.32;
		summarize.getSalesmanTotalSalesMap().put("Joana", 50000.0);
		summarize.getSalesmanTotalSalesMap().put(expectedWorstSellerSalesmanName, expectedWorstSellerTotalSales);
		summarize.getSalesmanTotalSalesMap().put("Claudio", 34000.0);

		summarizeService.updateWorstSeller();

		assertThat(summarize.getWorstSeller().getSalesmanName()).isEqualTo(expectedWorstSellerSalesmanName);
		assertThat(summarize.getWorstSeller().getTotal()).isEqualTo(expectedWorstSellerTotalSales);
	}

	@Test
	public void whenErrorOccurDuringUpdateWorstSeller_thenReturnException() throws Exception {
		assertThatThrownBy(() -> {
			summarizeService.updateWorstSeller();
		}).isInstanceOf(Exception.class);
	}

	@AfterEach
	public void clean() {
		summarize.clean();
	}
}
