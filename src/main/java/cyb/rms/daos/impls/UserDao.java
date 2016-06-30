package cyb.rms.daos.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cyb.rms.daos.IGenericDao;
import cyb.rms.daos.IUserDao;
import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	@Qualifier(value="userGenericDao")
	IGenericDao<User,Long> userGenericDao;
	
	@Override
	public User addUser(User usr) throws DaoException {
		
		return userGenericDao.save(usr);
	}

	@Override
	public User updateUser(User usr) throws DaoException {
		
		return userGenericDao.update(usr);
	}

	@Override
	public User removeUser(User usr) throws DaoException {
		usr=userGenericDao.get(usr.getId());// get user detail before removing 
		return userGenericDao.remove(usr);
	}

	@Override
	public List<User> listUsers() throws DaoException {
		
		return userGenericDao.list();
	}

	@Override
	public User getUser(long usrId) throws DaoException {
		
		return userGenericDao.get(usrId);
	}

	@Override
	public List<User> getUsersByName(String usrName) throws DaoException {
		String query = "Select u from User u where u.username like '%"+usrName+"%'";
		return userGenericDao.list(query);
	}

}
