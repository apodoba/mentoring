package com.apodoba.exception;

public class CinemaNotAvailable extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4093511877410771599L;

	public CinemaNotAvailable() {
		super("Cinema service not available. Try later");
	}
}
