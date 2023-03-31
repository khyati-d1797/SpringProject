package com.example.demo.utilities;

public class DateUtil {
	
	public static String getOrdinal(Integer date) {
		Integer num = date % 10;
		String ordinal = "th";
		switch(num) {
		case 1: 
			ordinal = "st";
			break;
		case 2: 
			ordinal = "nd";
			break;
		case 3: 
			ordinal = "rd";
			break;
		default: 
			ordinal = "th";
			break;
		}
		return date + ordinal;
	}

}
