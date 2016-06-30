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
	@Qualifier("userGenericDao")
	IGenericDao<User, Long> genUserDao;
	
	@Override
	public User getUserByUsername(String username) throws DaoException {
		String userQuery = "Select u from User u where u.username='"+username+"'";
		User user = genUserDao.getUniqueResult(userQuery);
		return user;
	}

	@Override
	public User saveUser(User user) throws DaoException {
		return genUserDao.save(user);
	}
	@Override
	public User updateUser(User usr) throws DaoException {
		return genUserDao.update(usr);
	}

	@Override
	public User removeUser(User usr) throws DaoException {
		usr=genUserDao.get(usr.getId());// get user detail before removing 
		return genUserDao.remove(usr);
	}

	@Override
	public List<User> listUsers() throws DaoException {
		return genUserDao.list();
	}

	@Override
	public User getUser(long usrId) throws DaoException {	
		return genUserDao.get(usrId);
	}

}
