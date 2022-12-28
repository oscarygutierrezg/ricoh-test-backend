package com.ricoh.test.exceptions;

public class InscripcionAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 413834373903248893L;

	public InscripcionAlreadyExistException(String msg){
		super(msg);
	}

}
