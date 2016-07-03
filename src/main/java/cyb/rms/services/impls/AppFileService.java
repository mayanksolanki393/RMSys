package cyb.rms.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cyb.rms.daos.IAppFileDao;
import cyb.rms.entities.AppFile;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IAppFileService;

@Component
public class AppFileService implements IAppFileService {

	@Autowired
	IAppFileDao appFileDao;
	
	@Override
	public AppFile addAppFile(AppFile appFile) throws DaoException {
		return appFileDao.addAppFile(appFile);
	}

	@Override
	public AppFile removeAppFile(AppFile appFile) throws DaoException {
		return appFileDao.removeAppFile(appFile);
	}

	@Override
	public AppFile updateAppFile(AppFile appFile) throws DaoException {
		return appFileDao.updateAppFile(appFile);
	}

	@Override
	public AppFile saveOrUpdateAppFile(AppFile appFile) throws DaoException {
		return appFileDao.saveOrUpdateAppFile(appFile);
	}

	@Override
	public AppFile findAppFileById(long id) throws DaoException {
		return appFileDao.getAppFile(id);
	}

}
