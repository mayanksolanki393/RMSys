package cyb.rms.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.Requirement;
import cyb.rms.exceptions.DaoException;

@Repository
public interface IRequirementDao {

	@Transactional
	public Requirement addRequirement(Requirement req) throws DaoException;
	
	@Transactional
	public Requirement updateRequirement(Requirement req) throws DaoException;
	
	@Transactional
	public Requirement removeRequirement(Requirement req) throws DaoException;

	@Transactional(readOnly=true)
	public List<Requirement> listRequirements() throws DaoException;

	@Transactional(readOnly=true)
	public Requirement getRequirement(long reqId) throws DaoException;

	
	
}
