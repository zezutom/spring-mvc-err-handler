package org.zezutom.spring_mvc_err_handler;

public class NoSuchPersonException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoSuchPersonException(Long id) {
		super("No person found using id " + id);
	}
	
}
