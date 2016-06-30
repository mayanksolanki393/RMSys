package cyb.rms.daos.impls;

import java.io.Serializable;

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

}
