package com.lbr.batchprocessing.model;

public class SalesMan {
	private String id;
	private Long cpf;
	private String name;
	private Double salary;

	public SalesMan() {
	}
	
	public SalesMan(String id, Long cpf, String name, Double salary) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vendendor [id=" + id + ", cpf=" + cpf + ", name=" + name + ", salary=" + salary + "]";
	}
}
