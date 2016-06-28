package cyb.rms.daos;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.exceptions.DaoException;

@Repository
public interface IGenericDao<E,PK extends Serializable>{
	
	@Transactional	
	public E save(E entity) throws DaoException;
	
	@Transactional
	public E saveOrUpdate(E entity) throws DaoException;
	
	@Transactional
	public E update(E entity) throws DaoException;
	
	@Transactional
	public E remove(E entity) throws DaoException;
	
	@Transactional(readOnly=true)
	public E get(PK id)throws DaoException;
	
	@Transactional(readOnly=true)
	public E getUniqueResult(String queryString) throws DaoException;
	
	@Transactional(readOnly=true)
	public List<E> list() throws DaoException;
	
	@Transactional(readOnly=true)
	public List<E> list(String queryString) throws DaoException;
}
