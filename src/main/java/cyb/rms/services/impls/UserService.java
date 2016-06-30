package cyb.rms.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cyb.rms.daos.IEmployeeDao;
import cyb.rms.daos.IUserDao;
import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IUserSevrice;

public class UserService implements IUserSevrice {

	
	@Autowired
	IUserDao userDao;
	@Override
	public User addUser(User user) throws DaoException {
		return userDao.addUser(user);
	}

	@Override
	public User removeUser(User user) throws DaoException {
	
		return userDao.removeUser(user);
	}

	@Override
	public User updateUser(User user) throws DaoException {
	
		return userDao.updateUser(user);
	}

	@Override
	public List<User> listUsers() throws DaoException {
	
		return userDao.listUsers();
	}

	@Override
	public User findUserById(long id) throws DaoException {
		
		return userDao.getUser(id);
	}

	@Override
	public List<User> findUsersByName(String userName) throws DaoException {
		
		return userDao.getUsersByName(userName);
	}

}
