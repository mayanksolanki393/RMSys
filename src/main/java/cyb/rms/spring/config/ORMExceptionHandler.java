package cyb.rms.spring.config;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ORMExceptionHandler {
		private final static Logger LOG = Logger.getLogger(ORMExceptionHandler.class);
		
	    @ResponseStatus(HttpStatus.CONFLICT)  // 409
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public void handleConflict(DataIntegrityViolationException ex) {
	    	LOG.error(ex);
	    }
	    
	    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
	    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
	    public @ResponseBody Exception handleConflict(JpaObjectRetrievalFailureException ex) {
	    	LOG.error(ex);
	    	return ex;
	    }
	    
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
	    @ExceptionHandler(Exception.class)
	    public void handleConflict(Exception ex) {
	    	LOG.error(ex.getCause());
	    }
	    
	    
	    
	
	  

}
