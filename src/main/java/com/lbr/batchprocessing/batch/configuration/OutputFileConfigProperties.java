package com.lbr.batchprocessing.batch.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "io.output")
public class OutputFileConfigProperties {
	private String dir;
	private String extension;
	private String delimiter;
	private OutputHeaderConfig header;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public OutputHeaderConfig getHeader() {
		return header;
	}

	public void setHeader(OutputHeaderConfig header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "OutputFileConfigProperties [dir=" + dir + ", extension=" + extension + ", delimiter=" + delimiter
				+ ", header=" + header + "]";
	}
}
