package com.lbr.batchprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lbr.batchprocessing.model.Customer;
import com.lbr.batchprocessing.model.SalesMan;
import com.lbr.batchprocessing.repository.CustomerRepository;
import com.lbr.batchprocessing.repository.SalesManRepository;
import com.lbr.batchprocessing.repository.SalesRepository;

@SpringBootApplication
public class BatchProcessingApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SalesManRepository salesManRepository;
	@Autowired
	private SalesRepository salesRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(BatchProcessingApplication.class, args);
	}
	
	  @Override
	  public void run(String... args) throws Exception {

//		customerRepository.deleteAll();
//		salesManRepository.deleteAll();
//		salesRepository.deleteAll();
//
//	    // save a couple of customers
//		customerRepository.save(new Customer("002", 2345675434544345L, "Jose da Silva", "Rural"));
//		customerRepository.save(new Customer("002", 2345675433444345L, "Eduardo Pereira", "Rural"));
//
//		salesManRepository.save(new SalesMan("001", 1234567891234L, "Pedro", Double.valueOf(50000)));
//		salesManRepository.save(new SalesMan("001", 3245678865434L, "Paulo", Double.valueOf(40000.99)));
//		salesRepository.deleteAll();
		

	    // fetch all customers
	    System.err.println("Customers found with findAll():");
	    System.err.println("-------------------------------");
	    for (Customer customer : customerRepository.findAll()) {
	      System.err.println(customer);
	    }
	    System.err.println();

//	    // fetch all customers
//	    System.err.println("SalesMan found with findAll():");
//	    System.err.println("-------------------------------");
//	    for (SalesMan salesMan : salesManRepository.findAll()) {
//	    	System.err.println(salesMan);
//	    }
//	    System.err.println();

	  }

}
