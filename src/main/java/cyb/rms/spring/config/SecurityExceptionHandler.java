package cyb.rms.spring.config;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SecurityExceptionHandler {

	private final static Logger LOG = Logger.getLogger(ORMExceptionHandler.class);
	
    @ResponseStatus(HttpStatus.FORBIDDEN)  // 403
    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody String handleConflict(AccessDeniedException ex) {
    	LOG.error(ex);
    	return "You don't have enough permissions to access this resource";
    }
}
