package com.lbr.batchprocessing.batch.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
@Component
@ConfigurationProperties(prefix = "batch")
public class BatchConfigProperties {

	private long schedulerInMilliseconds;

	public long getSchedulerInMilliseconds() {
		return schedulerInMilliseconds;
	}

	public void setSchedulerInMilliseconds(long schedulerInMilliseconds) {
		this.schedulerInMilliseconds = schedulerInMilliseconds;
	}

	@Override
	public String toString() {
		return "BatchConfigProperties [schedulerInMilliseconds=" + schedulerInMilliseconds + "]";
	}

}
