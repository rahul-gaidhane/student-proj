package in.example.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAopConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAopConfig.class);
	
	@Before("within(@org.springframework.stereotype.Repository *)" +
	        " || within(@org.springframework.stereotype.Service *)" +
	        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
		
		LOGGER.debug("In logging config before mail...");
    }
	
	
}
