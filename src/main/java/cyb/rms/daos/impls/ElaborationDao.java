package cyb.rms.daos.impls;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cyb.rms.daos.IElaborationDao;
import cyb.rms.daos.IGenericDao;
import cyb.rms.entities.Elaboration;
import cyb.rms.enums.RmsEnums.ElaborationStatus;
import cyb.rms.exceptions.DaoException;

@Repository
public class ElaborationDao implements IElaborationDao{

	@Autowired
	@Qualifier(value="elaboGenericDao")
	IGenericDao<Elaboration,Long> elabGenericDao;
	
	@Override
	public Elaboration addElaboration(Elaboration elab) throws DaoException {
		return elabGenericDao.save(elab);
	}

	@Override
	public Elaboration updateElaboration(Elaboration updElab) throws DaoException {
		Elaboration daoElabo = elabGenericDao.get(updElab.getId());
		BeanUtils.copyProperties(updElab, daoElabo);
		return elabGenericDao.update(daoElabo);
	}

	@Override
	public Elaboration removeElaboration(Elaboration elab) throws DaoException {
		elab.setStatus(ElaborationStatus.DELETED);
		return elabGenericDao.update(elab);
	}

	@Override
	public List<Elaboration> listElaborations() throws DaoException {
		return elabGenericDao.list();
	}

	@Override
	public Elaboration getElaboration(long elabId) throws DaoException {
		return elabGenericDao.get(elabId);
	}

}
