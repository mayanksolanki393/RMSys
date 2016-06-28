package cyb.rms.daos.impls;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cyb.rms.daos.IGenericDao;
import cyb.rms.exceptions.DaoException;

@Repository
@SuppressWarnings("unchecked")
public class GenericDao<E, PK extends Serializable> implements IGenericDao<E, PK> {

	@PersistenceContext
	private EntityManager em;
	private Class<E> typeParameterClass;

	public GenericDao(){}
	
	public GenericDao(Class<E> typeParameterClass) throws DaoException {
		this.typeParameterClass = typeParameterClass;
		
	}

	@Override
	public E save(E entity) throws DaoException {
		em.persist(entity);
		return entity;
	}

	@Override
	public E saveOrUpdate(E entity) throws DaoException {
		em.merge(entity);
		return entity;
	}

	@Override
	public E update(E entity) throws DaoException {
		em.merge(entity);
		return entity;
	}

	@Override
	public E remove(E entity) throws DaoException {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		return entity;
	}

	@Override
	public E get(PK id) throws DaoException {
		Object entity;
		entity = em.find(typeParameterClass, id);
		if(entity==null){
			throw new EntityNotFoundException("Could not find entity of id : "+id);
		}
		return (E) entity;
	}

	@Override
	public E getUniqueResult(String queryString) throws DaoException {
		Query query = em.createQuery(queryString);
		Object entitiy = query.getSingleResult();
		return (E) entitiy;
	}

	@Override
	public List<E> list() throws DaoException {

		Query query = em.createNamedQuery(getRealQueryName("list"),typeParameterClass);
		List<Object> entities = query.getResultList();

		if (entities != null) {
			return (List<E>) entities;
		} else {
			throw new EntityNotFoundException("No enity with specified query \"" + query.toString()+ "\" found");
		}

	}

	@Override
	public List<E> list(String queryStr) throws DaoException {

		Query query = em.createQuery(queryStr);

		List<Object> entities = query.getResultList();

		if (entities != null) {
			return (List<E>) entities;
		} else {
			throw new EntityNotFoundException(
					"No enity with specified query \"" + query.toString()
							+ "\" found");
		}
	}
	
	protected String getRealQueryName(String queryName){
		return typeParameterClass.getSimpleName()+"."+queryName;
	}
}
