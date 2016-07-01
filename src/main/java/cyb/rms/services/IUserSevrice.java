package cyb.rms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;

@Service
@Transactional
public interface IUserSevrice {
	
	public User addUser(User user) throws DaoException;

	public User removeUser(User user) throws DaoException;

	public User updateUser(User user) throws DaoException;

	@Transactional(readOnly=true)
	public List<User> listUsers() throws DaoException;

	@Transactional(readOnly=true)
	public User findUserById(long id) throws DaoException;

	@Transactional(readOnly=true)
	public User findUsersByUsername(String userName) throws DaoException;

}
