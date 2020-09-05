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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.lbr.batchprocessing.model.Salesman;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalesmanCustomRepositoryTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SalesmanCustomRepository salesmanCustomRepository;

	@BeforeEach
	void setup() {
		cleanCollection();
		List<Salesman> salespeople = new ArrayList<>();
		salespeople.add(new Salesman(11156754334L, "Angela Pereira", 2330.00));
		salespeople.add(new Salesman(11156754335L, "Eduardo Pereira", 2000.00));
		salespeople.add(new Salesman(11156754336L, "Carlos Pereira", 1000.00));
		salespeople.add(new Salesman(11156754337L, "Emanuel Pereira", 3300.00));
		salespeople.add(new Salesman(11156754338L, "Saulo Pereira", 2002.00));
		salespeople.add(new Salesman(11156754339L, "Gorette Pereira", 2050.00));
		salespeople.add(new Salesman(11156754340L, "John Pereira", 2330.00));
		salespeople.add(new Salesman(11156754334L, "Angela Pereira", 2330.00));
		salespeople.add(new Salesman(11156754342L, "Marcelo Pereira", 2000.00));

		salespeople.forEach(mongoTemplate::save);
	}

	private void cleanCollection() {
		mongoTemplate.dropCollection(Salesman.class);
	}

	@AfterEach
	void clean() {
		cleanCollection();
	}

	@Test
	public void whenCountDistinctByCpf_thenReturnCorrect() {
		Long countExpected = 8L;
		assertEquals(countExpected, salesmanCustomRepository.countDistinctByCpf());
	}

}
