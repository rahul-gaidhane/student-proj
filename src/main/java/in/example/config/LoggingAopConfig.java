package in.example.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAopConfig {
	
	@Before("within(@org.springframework.stereotype.Repository *)" +
	        " || within(@org.springframework.stereotype.Service *)" +
	        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut(JoinPoint joinPoint) {
		
		log.debug("Advice executed for the before the method[{}] of the class[{}] gets executed...", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getCanonicalName());
    }
	
	
}
