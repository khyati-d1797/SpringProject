package com.example.demo.utilities;

public class Logger {
	
	public static void log(Object... obj) {
		Integer length = obj.length;
		String log = "";
		for(int i=0;i<length;i++) {
			if(i>0) {
				log+= ", "+obj[i].toString();
			}else {
				log+= obj[i].toString();
			}
		}
		System.out.println(log);
	}
	
	public static void logSanitized(Object... obj) {
		Integer length = obj.length;
		String log = "";
		for(int i=0;i<length;i++) {
			if(i>0) {
				log+= ", "+obj[i].toString();
			}else {
				log+= obj[i].toString();
			}
			
//			System.out.println();
		}
		System.out.println(log);
	}

}
