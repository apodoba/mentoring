package com.classloader.task;

import java.util.ResourceBundle;

public class Util {
	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("classloader");
	
	public static String getParam(String name){
		return RESOURCE_BUNDLE.getString(name);
	}
}
