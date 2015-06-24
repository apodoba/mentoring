package com.apodoba.shop.exception;

public class CardNotExistException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6743530520357085273L;

	public CardNotExistException() {
		super("Card with such parameters not exist");
	}
}
