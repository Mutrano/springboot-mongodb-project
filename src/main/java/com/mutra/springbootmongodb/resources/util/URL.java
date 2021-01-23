package com.mutra.springbootmongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text,"UTF-8");
		}
		catch(UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static LocalDate convertDate(String textDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(!textDate.isBlank()) {
			return LocalDate.parse(textDate,dtf);
		}
		else {
			return LocalDate.now();
		}
	}
}
