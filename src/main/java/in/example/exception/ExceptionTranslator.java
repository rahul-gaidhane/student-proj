package in.example.exception;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTranslator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTranslator.class);
	
	@ExceptionHandler(value = { EntityNotFoundException.class})
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
		LOGGER.error("Service to handle the entity not found exception : {}", ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
