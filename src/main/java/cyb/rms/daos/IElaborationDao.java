package cyb.rms.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.Elaboration;
import cyb.rms.exceptions.DaoException;

@Repository
public interface IElaborationDao {
	
	@Transactional
	public Elaboration addElaboration(Elaboration elab) throws DaoException;
	
	@Transactional
	public Elaboration updateElaboration(Elaboration elab) throws DaoException;
	
	@Transactional
	public Elaboration removeElaboration(Elaboration elab) throws DaoException;

	@Transactional(readOnly=true)
	public List<Elaboration> listElaborations() throws DaoException;

	@Transactional(readOnly=true)
	public Elaboration getElaboration(long elabId) throws DaoException;
	
}
