package cyb.rms.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.AppFile;
import cyb.rms.exceptions.DaoException;

@Repository
public interface IAppFileDao {

	@Transactional
	public AppFile addAppFile(AppFile apfl) throws DaoException;
	
	@Transactional
	public AppFile updateAppFile(AppFile apfl) throws DaoException;
	
	@Transactional
	public AppFile saveOrUpdateAppFile(AppFile apfl) throws DaoException;

	@Transactional
	public AppFile removeAppFile(AppFile apfl) throws DaoException;

	@Transactional(readOnly=true)
	public List<AppFile> listAppFile() throws DaoException;

	@Transactional(readOnly=true)
	public AppFile getAppFile(long apflId) throws DaoException;

	
}
