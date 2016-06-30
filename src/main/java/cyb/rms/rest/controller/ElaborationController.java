package cyb.rms.rest.controller;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IEmployeeService;

@RestController
@RequestMapping(path="/elaboration")
public class ElaborationController {
	
	@RequestMapping(method=RequestMethod.PUT)
	public Elaboration addElaboration(@RequestBody Elaboration elaboration)
	{
		return elaboration;
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="elaboId")
	public Elaboration removeElaboration(@PathVariable long elabId)
	{
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Elaboration updateElaboration(@RequestBody Elaboration Elaboration)
	{
		return Elaboration;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/reqId")
	public Set<Elaboration> getAllElaborationsByReqId(@PathVariable long projId)
	{
		return null;
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/elaboId")
	public Elaboration getElaborationsById(@PathVariable long reqId)
	{
		return null;
		
	}
	

}
