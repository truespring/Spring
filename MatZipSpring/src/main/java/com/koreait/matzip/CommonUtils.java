package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class CommonUtils {
	public static int parseStrToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			
		} return 0;
	}
	
	public static double parseStrToDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch(Exception e) {
			
		} return 0;
	}
	
	public static int getIntParameter(String key, HttpServletRequest request) {
		return parseStrToInt(request.getParameter(key));
	}
	
	public static int getIntParameter(String key, MultipartRequest request) {
		return parseStrToInt(request.getParameter(key));
	}
	
	public static double getDoubleParameter(String key, HttpServletRequest request) {
		return parseStrToDouble(request.getParameter(key));
	}
}
