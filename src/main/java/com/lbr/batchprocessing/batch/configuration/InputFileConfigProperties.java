package com.lbr.batchprocessing.batch.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "io.input")
public class InputFileConfigProperties {
	@Value("${io.input.file}")
	private Resource[] file;
	private String delimiter;
	private String salesmanLineId;
	private String customerLineId;
	private String saleLineId;
	private Integer chunk;

	public Resource[] getFile() {
		return file;
	}

	public void setFile(Resource[] file) {
		this.file = file;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getSalesmanLineId() {
		return salesmanLineId;
	}

	public void setSalesmanLineId(String salesmanLineId) {
		this.salesmanLineId = salesmanLineId;
	}

	public String getCustomerLineId() {
		return customerLineId;
	}

	public void setCustomerLineId(String customerLineId) {
		this.customerLineId = customerLineId;
	}

	public String getSaleLineId() {
		return saleLineId;
	}

	public void setSaleLineId(String saleLineId) {
		this.saleLineId = saleLineId;
	}

	public Integer getChunk() {
		return chunk;
	}

	public void setChunk(Integer chunk) {
		this.chunk = chunk;
	}

	@Override
	public String toString() {
		return "InputFileConfigProperties [file=" + Arrays.toString(file) + ", delimiter=" + delimiter
				+ ", salesmanLineId=" + salesmanLineId + ", customerLineId=" + customerLineId + ", saleLineId="
				+ saleLineId + ", chunk=" + chunk + "]";
	}

}
