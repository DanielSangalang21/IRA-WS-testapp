package tera.daniel.instapayws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED)
public class BankSignException extends RuntimeException {
	
	public BankSignException(String message) {
		super(message);
	}
}
