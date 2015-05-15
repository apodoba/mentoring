package com.classloader.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger(Main.class);
	
	private static Map<String, ClassLoader> oldClasses = new HashMap<String, ClassLoader>();
	private static Map<String, ClassLoader> newClasses = new HashMap<String, ClassLoader>();
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception  {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LOGGER.info("Start programm. Possible commands:");
		LOGGER.info("1 - load new/exist class and run method");
		LOGGER.info("2 - reload class and run new method");
		
		while (true) {
			LOGGER.info("Enter command, class name, method and params (split by space): ");
			String answer = br.readLine();
			LOGGER.warn("You enter:  "+answer);
			
			if("Exit".equals(answer)) break;
			if(StringUtils.isBlank(answer)){
				LOGGER.info("Answer is empty");
				continue;
			} 
			
			String[] arguments = answer.split(" ");			
			if(arguments.length < 3){
				LOGGER.info("You should enter command, class name and method");
				continue;
			} 
			
			if("1".equals(arguments[0])){
				loadOldClass(arguments);
			}else if("2".equals(arguments[0])){
				loadNewClass(arguments);
			}else{
				LOGGER.info("Invalid command");
				continue;
			}
		}
		br.close();
	}
	
	private static void loadOldClass(String[] arguments){	
		String className = arguments[1];
		ClassLoader myClassLoader = null;
		if(oldClasses.keySet().contains(className)){
			myClassLoader = oldClasses.get(className);
		}else{
			myClassLoader = new MyClassLoader();
			oldClasses.put(className, myClassLoader);
		}
		loadClass(arguments, myClassLoader);
	}
	
	
	private static void loadNewClass(String[] arguments){
		String className = arguments[1];
		ClassLoader newClassLoader = new MyClassLoader();
		
		if(oldClasses.keySet().contains(className) && newClasses.keySet().contains(className)){
			oldClasses.put(className, newClasses.get(className));
		}
		if(oldClasses.keySet().contains(className) ){
			newClasses.put(className, newClassLoader);
		}else{
			oldClasses.put(className, newClassLoader);
		}
		
		loadClass(arguments, newClassLoader);
	}
	
	@SuppressWarnings("rawtypes")
	private static void loadClass(String[] arguments, ClassLoader classLoader){
		Class clazz = getClass(new String[]{arguments[1]}, classLoader);
		
		if(clazz == null) return;
		
		Object methodResult = callMethod(clazz, arguments);
		if(methodResult != null)  LOGGER.info(methodResult == "" ? "Void method" : "Result " + methodResult);
	}
	
	@SuppressWarnings("rawtypes")
	private static Class getClass(String[] arguments, ClassLoader classLoader){
		try {
			return Class.forName(arguments[0], true, classLoader);
		} catch (ClassNotFoundException e) {
			LOGGER.info("Class not found: " + arguments[0]);
			LOGGER.error("Class not found: " + arguments[0], e);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private static Object callMethod(Class clazz, String[] arguments){
		Method callMethod = null;
		Object[] params = arguments.length - 3 > 0 ? Arrays.copyOfRange(arguments, 3, arguments.length) : null;

		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(arguments[2]) && methods[i].getParameterTypes().length == arguments.length - 3) {
				callMethod = methods[i];
			}
		}
		try {
			Object result = callMethod.invoke(clazz.newInstance(), params);
			return result==null ? "" : result;
		} catch (Exception e) {
			LOGGER.info("Can not invoke method: " + arguments[2]);
			LOGGER.error("Can not invoke method: " + arguments[2], e);
		} 
		return null;
	}
}
