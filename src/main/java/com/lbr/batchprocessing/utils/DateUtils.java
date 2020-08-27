package com.lbr.batchprocessing.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author luan.barbosa.ramalho
 *
 */
public final class DateUtils {

	private DateUtils() {
	}

	public static String localDateTimeToyyyyMMddHHmmss(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formatDateTime = localDateTime.format(formatter);
		return formatDateTime;
	}

}
