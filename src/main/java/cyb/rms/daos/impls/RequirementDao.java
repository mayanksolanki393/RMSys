package cyb.rms.daos.impls;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cyb.rms.daos.IGenericDao;
import cyb.rms.daos.IRequirementDao;
import cyb.rms.entities.Requirement;
import cyb.rms.enums.RmsEnums.RequirementStatus;
import cyb.rms.exceptions.DaoException;

@Repository
public class RequirementDao implements IRequirementDao {

	@Autowired
	@Qualifier(value="requireGenericDao")
	IGenericDao<Requirement,Long> reqGenericDao;
	
	@Override
	public Requirement addRequirement(Requirement req) throws DaoException {
		return reqGenericDao.save(req);
	}

	@Override
	public Requirement updateRequirement(Requirement req) throws DaoException {
		return reqGenericDao.update(req);
	}

	@Override
	public Requirement removeRequirement(Requirement req) throws DaoException {
		req.setStatus(RequirementStatus.DELETED);
		return reqGenericDao.update(req);
	}

	@Override
	public List<Requirement> listRequirements() throws DaoException {
		return reqGenericDao.list();
	}

	@Override
	public Requirement getRequirement(long reqId) throws DaoException {
		return reqGenericDao.get(reqId);
	}

}
