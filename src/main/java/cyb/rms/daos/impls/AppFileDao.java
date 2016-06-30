package cyb.rms.daos.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cyb.rms.daos.IAppFileDao;
import cyb.rms.daos.IGenericDao;
import cyb.rms.entities.AppFile;
import cyb.rms.enums.RmsEnums.FileStatus;
import cyb.rms.exceptions.DaoException;

@Repository
public class AppFileDao implements IAppFileDao{

	@Autowired
	@Qualifier(value="appFileGenericDao")
	IGenericDao<AppFile, Long> appFileGenericDao;
	
	@Override
	public AppFile addAppFile(AppFile apfl) throws DaoException {
		
		return appFileGenericDao.save(apfl);
	}

	@Override
	public AppFile updateAppFile(AppFile apfl) throws DaoException {
		
		return appFileGenericDao.update(apfl);
	}

	@Override
	public AppFile removeAppFile(AppFile apfl) throws DaoException {
		
		apfl.setStatus(FileStatus.DELETED);
		return appFileGenericDao.update(apfl);
	}

	@Override
	public List<AppFile> listAppFile() throws DaoException {
		
		return appFileGenericDao.list();
	}

	@Override
	public AppFile getAppFile(long apflId) throws DaoException {
		
		return appFileGenericDao.get(apflId);
	}

}
