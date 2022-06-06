package in.example.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionTranslator extends BaseExceptionTranslator {
	
	@ExceptionHandler(value = { EntityNotFoundException.class})
	public BaseExceptionTranslator.ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
		log.error("Service to handle the entity not found exception : {}", ex.getMessage());
		
		return new BaseExceptionTranslator.ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
}
