package com.lastminute.commons;

public class StringUtils {
	
    private StringUtils() {}
	
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

}
