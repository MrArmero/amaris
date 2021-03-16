package com.amaris.masa.inditex.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception por defecto para los casos en los que no se encuentre un registro válido.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
