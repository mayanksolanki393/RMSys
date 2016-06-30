package cyb.rms.services;

import java.util.List;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;

public interface IUserSevrice {
	
	public User addUser(User user) throws DaoException;

	public User removeUser(User user) throws DaoException;

	public User updateUser(User user) throws DaoException;

	public List<User> listUsers() throws DaoException;

	public User findUserById(long id) throws DaoException;

	public List<User> findUsersByName(String userName) throws DaoException;

}
