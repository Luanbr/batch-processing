package com.lbr.batchprocessing.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.lbr.batchprocessing.model.BiggestSale;
import com.lbr.batchprocessing.model.Item;
import com.lbr.batchprocessing.model.Sale;
import com.lbr.batchprocessing.model.WorstSalesman;

//@TestPropertySource(properties = { "io.input.file=file://${user.home}/*.dat" })
@SpringBootTest
@ActiveProfiles("test")
public class SaleCustomRepositoryTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SaleCustomRepository saleCustomRepository;

	@BeforeEach
	public void setup() {
		cleanCollection();
		List<Sale> sales = new ArrayList<>();
		List<Item> itemsSaleOne = new ArrayList<>();
		List<Item> itemsSaleTwo = new ArrayList<>();

		itemsSaleOne.add(new Item(10L, 62L, 899.78));
		itemsSaleOne.add(new Item(11L, 2L, 88.0));
		itemsSaleTwo.add(new Item(12L, 22L, 899.78));
		itemsSaleTwo.add(new Item(13L, 10L, 88.0));

		sales.add(new Sale(12222225L, itemsSaleOne, "Denivaldo"));
		sales.add(new Sale(00022225L, itemsSaleTwo, "Carlos"));

		sales.forEach(c -> {
			mongoTemplate.save(c);
		});
	}

	private void cleanCollection() {
		mongoTemplate.dropCollection(Sale.class);
	}

	@AfterEach
	public void clean() {
		cleanCollection();
	}

	@Test
	public void whenFindBiggestSale_thenReturnCorrect() {
		Long expectedSaleId = 12222225L;
		Double expectedTotal = 55962.00;
		BiggestSale biggestSale = saleCustomRepository.findBiggestSale();

		assertEquals(expectedSaleId, biggestSale.getSaleId());
		assertEquals(expectedTotal, biggestSale.getTotal());
	}

	@Test
	public void whenFindWorstSeller_thenReturnCorrect() {
		String expectedSalesmanName = "Carlos";
		Double expectedTotalSold = 20675.00;
		WorstSalesman worstSalesman = saleCustomRepository.findWorstSalesman();

		assertEquals(expectedSalesmanName, worstSalesman.getSalesmanName());
		assertEquals(expectedTotalSold, worstSalesman.getTotal());
	}

}
