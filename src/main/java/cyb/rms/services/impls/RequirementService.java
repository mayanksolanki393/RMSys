package cyb.rms.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyb.rms.daos.IEmployeeDao;
import cyb.rms.daos.IRequirementDao;
import cyb.rms.entities.Requirement;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IRequirementService;

@Service
public class RequirementService implements IRequirementService {

	@Autowired
	IRequirementDao reqDao;
	
	@Override
	public Requirement addRequirement(Requirement req) throws DaoException {
		return reqDao.addRequirement(req);
	}

	@Override
	public Requirement removeRequirement(Requirement req) throws DaoException {
		return reqDao.removeRequirement(req);
	}

	@Override
	public Requirement updateRequirement(Requirement req) throws DaoException {
		return reqDao.updateRequirement(req);
	}

	@Override
	public List<Requirement> listRequirements() throws DaoException {
		return reqDao.listRequirements();
	}

	@Override
	public Requirement findRequirementById(long id) throws DaoException {
		return reqDao.getRequirement(id);
	}
}
