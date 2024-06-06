package tera.daniel.instapayws.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BankSignException.class)
	public final ResponseEntity<ErrorDetails> handleBankSignException(
			Exception ex, WebRequest request) {
			ErrorDetails details = new ErrorDetails(ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.ACCEPTED);
	}
}
