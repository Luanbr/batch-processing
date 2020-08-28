package com.lbr.batchprocessing.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lbr.batchprocessing.model.Customer;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomerCustomRepositoryTest {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CustomerCustomRepository repository;

	@BeforeEach
	void setup() {
		clean();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2345675434544345L, "Jose da Silva", "Rural"));
		customers.add(new Customer(1115675433444345L, "Eduardo Pereira", "Rural"));
		customers.add(new Customer(0005675434544345L, "Luan Barbosa", "Rural"));
		customers.add(new Customer(2345675434544345L, "Jose da Silva", "Rural"));
		customers.add(new Customer(1115675433444345L, "Eduardo Pereira", "Rural"));
		customers.add(new Customer(0005675434544345L, "Luan Barbosa", "Rural"));
		customers.add(new Customer(2345675434544345L, "Jose da Silva", "Rural"));

		customers.forEach(c -> {
			mongoTemplate.save(c);
		});
	}
	
	@AfterEach
	void clean() {
		mongoTemplate.dropCollection(Customer.class);
	}
	
	@Test
	public void whenCountDistinctByCnpj_thenReturnCorrect() {
		Long countExpected = 3L;
		assertEquals(countExpected, repository.countDistinctByCnpj());
	}

}
