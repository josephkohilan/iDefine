package com.deloitte.ievolve.utility;

public class UtilMethods {
	
	public static boolean isNullOrEmpty(String input) {
		if(null!=input && !input.equalsIgnoreCase(UtilConstants.EMPTY)) {
			return false;
		}
		return true;
	}

}
