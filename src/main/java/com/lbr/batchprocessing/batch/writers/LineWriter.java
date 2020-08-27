package com.lbr.batchprocessing.batch.writers;

interface LineWriter<T> {
	public void write(Object item) throws Exception;

}
