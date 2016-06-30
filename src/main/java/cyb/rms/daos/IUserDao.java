package cyb.rms.daos;

import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;

public interface IUserDao {
	public User getUserByUsername(String username) throws DaoException;
	public User saveUser(User user) throws DaoException;
}
