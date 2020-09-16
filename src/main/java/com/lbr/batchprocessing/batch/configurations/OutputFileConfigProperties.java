package com.lbr.batchprocessing.batch.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
@ConfigurationProperties(prefix = "io.output")
public class OutputFileConfigProperties {
	private String dir;
	private String extension;
	private String delimiter;

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

	@Override
	public String toString() {
		return "OutputFileConfigProperties [dir=" + dir + ", extension=" + extension + ", delimiter=" + delimiter + "]";
	}
}
