package cyb.rms.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cyb.rms.daos.IElaborationDao;
import cyb.rms.daos.IEmployeeDao;
import cyb.rms.entities.Elaboration;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IElaborationService;

public class ElaborationService implements IElaborationService {

	
	@Autowired
	IElaborationDao elDao;
	@Override
	public Elaboration addElaboration(Elaboration elab) throws DaoException {
		return elDao.addElaboration(elab);
	}

	@Override
	public Elaboration removeElaboration(Elaboration elab) throws DaoException {
		return elDao.removeElaboration(elab);
	}

	@Override
	public Elaboration updateElaboration(Elaboration elab) throws DaoException {
		return elDao.updateElaboration(elab);
	}

	@Override
	public List<Elaboration> listElaborations() throws DaoException {
		return elDao.listElaborations();
	}

	@Override
	public Elaboration findElaborationById(long id) throws DaoException {
		return elDao.getElaboration(id);
	}

	@Override
	public List<Elaboration> findElaborationsByName(String elabName) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
