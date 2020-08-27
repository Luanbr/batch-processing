package com.lbr.batchprocessing.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateUtilsTest {

	@Test
	public void whenLocalDateTimeToyyyyMMddHHmmssCalled_thenReturnCorrectFormat() {
		String strDate = "2020-08-25 14:31:00";
		String expectedStrDate = "20200825143100";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		assertEquals(expectedStrDate, DateUtils.localDateTimeToyyyyMMddHHmmss(LocalDateTime.parse(strDate, formatter)));
	}
}
