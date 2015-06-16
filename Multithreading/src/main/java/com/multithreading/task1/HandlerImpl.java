package com.multithreading.task1;

public class HandlerImpl implements Handler{

	private final String PRINT_VALUE;
	
	public HandlerImpl(String value) {
		PRINT_VALUE = value;
	}
	
	public void execute(){
		System.out.println(PRINT_VALUE);
	}

	@Override
	public String getValue() {
		return PRINT_VALUE;
	}

}
