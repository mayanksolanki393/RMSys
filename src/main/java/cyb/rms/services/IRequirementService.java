package cyb.rms.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.Requirement;
import cyb.rms.exceptions.DaoException;

@Service
@Transactional
public interface IRequirementService {
	
	@Secured("ROLE_USER")
	public Requirement addRequirement(Requirement req) throws DaoException;

	@Secured("ROLE_USER")
	public Requirement removeRequirement(Requirement req) throws DaoException;

	@Secured("ROLE_USER")
	public Requirement updateRequirement(Requirement req) throws DaoException;

	@Transactional(readOnly=true)
	@Secured("ROLE_USER")
	public List<Requirement> listRequirements() throws DaoException;

	@Transactional(readOnly=true)
	@Secured("ROLE_USER")
	public Requirement findRequirementById(long id) throws DaoException;

}
