package org.roper;

public class StatusBar {
	static String str = ""; 
	
	static void reset() {
		str = "";
	}
	
	static void show(String sstr) {
		str = sstr;
	}
}
