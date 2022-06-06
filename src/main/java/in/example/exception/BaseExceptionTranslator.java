package in.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseExceptionTranslator {
	
	@ExceptionHandler(value = { Throwable.class})
	public ResponseEntity<String> handleThrowable(Throwable ex) {
		log.error("Service to handle the throwable exception : {}", ex.getMessage());
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
