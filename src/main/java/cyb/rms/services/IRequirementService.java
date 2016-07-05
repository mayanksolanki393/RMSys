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

	public Requirement addRequirement(Requirement req) throws DaoException;

	public Requirement removeRequirement(Requirement req) throws DaoException;

	public Requirement updateRequirement(Requirement req) throws DaoException;

	@Transactional(readOnly=true)
	public List<Requirement> listRequirements() throws DaoException;

	@Transactional(readOnly=true)
	public Requirement findRequirementById(long id) throws DaoException;

}
