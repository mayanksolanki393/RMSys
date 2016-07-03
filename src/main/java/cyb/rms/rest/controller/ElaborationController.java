package cyb.rms.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.entities.Elaboration;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IElaborationService;
import cyb.rms.services.IRequirementService;

@RestController
@RequestMapping(path = "/elaboration")
@Transactional
public class ElaborationController {

	@Autowired
	IElaborationService elaboService;

	@Autowired
	IRequirementService requiService;
	
	static Logger LOG = Logger.getLogger(ElaborationController.class);
	
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Elaboration addElaboration(@RequestBody Elaboration elaboration) throws DaoException {
		return elaboService.addElaboration(elaboration);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{elaboId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Elaboration removeElaboration(@PathVariable("elaboId") long elabId) throws DaoException {
		Elaboration elaboration = elaboService.findElaborationById(elabId);
		return elaboService.removeElaboration(elaboration);
	}

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Elaboration updateElaboration(@RequestBody Elaboration elaboration)  throws DaoException{
		return elaboService.updateElaboration(elaboration);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{reqId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<Elaboration> getAllElaborationsByReqId(@PathVariable("reqId") long reqId) throws DaoException {
		return requiService.findRequirementById(reqId).getElaborations();

	}

	@RequestMapping(method = RequestMethod.GET, path = "/{elaboId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Elaboration getElaborationsById(@PathVariable("elaboId") long elaboId) throws DaoException{
		return elaboService.findElaborationById(elaboId);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)// 404
	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	public @ResponseBody String handleConflict(
			JpaObjectRetrievalFailureException ex) {
		LOG.error(ex);
		return "We could not find the elaboration you are looking for may be it was deleted by a member of your team";
	}

}
