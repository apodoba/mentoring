package com.apodoba.shop.exception;

public class NotEnoughMoneyException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3868203518160518652L;

	public NotEnoughMoneyException() {
		super("Not enough money for purchase");
	}
}

