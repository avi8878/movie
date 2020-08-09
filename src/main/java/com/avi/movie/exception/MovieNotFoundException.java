package com.avi.movie.exception;

/**
*<h1>MovieNotFoundException will be thrown when there is no resource for given id in server</h1>
*@author avinashsingh
*/   
public class MovieNotFoundException extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public MovieNotFoundException() {
		super();
	}

	public MovieNotFoundException(final String message) {
		super(message);
	}

}