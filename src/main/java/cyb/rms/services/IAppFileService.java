package cyb.rms.services;

import cyb.rms.entities.AppFile;
import cyb.rms.exceptions.DaoException;

public interface IAppFileService {
	public AppFile addAppFile(AppFile appFile) throws DaoException;
	public AppFile removeAppFile(AppFile appFile) throws DaoException;
	public AppFile updateAppFile(AppFile appFile) throws DaoException;
	public AppFile saveOrUpdateAppFile(AppFile appFile) throws DaoException;
	public AppFile findAppFileById(long id) throws DaoException;
}
