package cyb.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method = RequestMethod.PUT)
	public Elaboration addElaboration(@RequestBody Elaboration elaboration) throws DaoException {
		return elaboService.addElaboration(elaboration);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{elaboId}")
	public Elaboration removeElaboration(@PathVariable("elaboId") long elabId) throws DaoException {
		Elaboration elaboration = elaboService.findElaborationById(elabId);
		return elaboService.removeElaboration(elaboration);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Elaboration updateElaboration(@RequestBody Elaboration elaboration)  throws DaoException{
		return elaboService.updateElaboration(elaboration);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{reqId}")
	public List<Elaboration> getAllElaborationsByReqId(@PathVariable("reqId") long reqId) throws DaoException {
		return requiService.findRequirementById(reqId).getElaborations();

	}

	@RequestMapping(method = RequestMethod.GET, path = "/{elaboId}")
	public Elaboration getElaborationsById(@PathVariable("elaboId") long elaboId) throws DaoException{
		return elaboService.findElaborationById(elaboId);
	}

}
