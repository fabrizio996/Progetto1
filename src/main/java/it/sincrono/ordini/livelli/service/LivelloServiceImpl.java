package it.sincrono.ordini.livelli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.sincrono.ordini.entities.Livello;
import it.sincrono.ordini.exceptions.ServiceErrors;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.livelli.dto.LivelloOrdini;
import it.sincrono.ordini.livelli.dto.LivelloSommaImporti;
import it.sincrono.ordini.livelli.repository.LivelloRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LivelloServiceImpl implements LivelloService {

	private static final String TAG = LivelloServiceImpl.class.getName();

	@Autowired
	private LivelloRepository repository;

	@Override
	public List<LivelloOrdini> getLivelloOrdini() throws ServiceException {
		List<LivelloOrdini> result;

		try {
			result = repository.getLivelloOrdini();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public void addLivello(Livello livello) throws ServiceException {
		try {
			repository.save(livello);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}
	}

	@Override
	public List<Livello> getAllLivelli() throws ServiceException {
		List<Livello> result;

		try {
			result = repository.findAll();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public Livello getLivello(Integer id) throws ServiceException {
		Livello livello;

		try {
			livello = repository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return livello;
	}

	@Override
	public void updateLivello(Livello livello) throws ServiceException {
		if (repository.existsById(livello.getId())) {
			try {
				repository.save(livello);
			} catch (Exception e) {
				log.error(TAG + "{}", e);
				throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
			}
		} else {
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		}
	}

	@Override
	public void deleteLivello(Integer id) throws ServiceException {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}
	}

	@Override
	public List<LivelloSommaImporti> getLivelloSommaImporti() throws ServiceException {

		List<LivelloSommaImporti> result;

		try {
			result = repository.getLivelloSommaImporti();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;

	}

}
