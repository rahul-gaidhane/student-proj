package in.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseExceptionTranslator {
	
	@ExceptionHandler(value = { Throwable.class})
	public ErrorResponse handleThrowable(Throwable ex) {
		log.error("Service to handle the throwable exception : {}", ex.getMessage());
		
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
	}
	
	@Data
    public static class ErrorResponse {
        private final String httpCodeMessage;
        private final String message;
    }
}
