package cyb.rms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;

@Service
public interface IElaborationService {
	
	public Elaboration addElaboration(Elaboration elab) throws DaoException;

	public Elaboration removeElaboration(Elaboration elab) throws DaoException;

	public Elaboration updateElaboration(Elaboration elab) throws DaoException;

	public List<Elaboration> listElaborations() throws DaoException;

	public Elaboration findElaborationById(long id) throws DaoException;

}
