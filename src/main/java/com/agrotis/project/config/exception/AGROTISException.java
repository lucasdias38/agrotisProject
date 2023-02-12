package com.agrotis.project.config.exception;

@SuppressWarnings("serial")
public class AGROTISException extends RuntimeException {

	public AGROTISException(Object id) {
		super("Resource not found. Id: " + id);
	}
	
	public AGROTISException(String msg) {
		super(msg);
	}
	
}
