package cyb.rms.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;

@Repository
public interface IUserDao {
	
	@Transactional
	public User getUserByUsername(String username) throws DaoException;
	
	@Transactional
	public User saveUser(User user) throws DaoException;
	
	@Transactional
	public User updateUser(User usr) throws DaoException;
	
	@Transactional
	public User removeUser(User usr) throws DaoException;

	@Transactional(readOnly=true)
	public List<User> listUsers() throws DaoException;

	@Transactional(readOnly=true)
	public User getUser(long usrId) throws DaoException;
	
}
