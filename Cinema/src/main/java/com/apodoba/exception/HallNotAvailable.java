package com.apodoba.exception;

public class HallNotAvailable extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3087992041850672272L;

	public HallNotAvailable() {
		super("Hall in cinema not available. Try later");
	}
}
