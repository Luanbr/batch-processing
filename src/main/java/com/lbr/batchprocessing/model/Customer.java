package com.lbr.batchprocessing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {
	@Id
	private String _id;
	private String id;
	private Long cnpj;
	private String name;
	private String businessArea;

	public Customer() {
	}

	public Customer(String id, Long cnpj, String name, String businessArea) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cnpj=" + cnpj + ", name=" + name + ", businessArea=" + businessArea + "]";
	}
}
