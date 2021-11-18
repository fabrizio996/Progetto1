package it.sincrono.ordini.ordini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.entities.Ordine;
import it.sincrono.ordini.exceptions.ServiceErrors;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.ordini.repository.OrdineRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdineServiceImpl implements OrdineService {
	@Autowired
	private OrdineRepository repository;

	private static final String TAG = OrdineServiceImpl.class.getName();

	@Override
	public List<Ordine> getOrdineMese(Integer mese) throws ServiceException {
		List<Ordine> result;

		try {
			result = repository.getOrdineMese(mese);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public void addOrdine(Ordine ordine) throws ServiceException {
		try {
			repository.save(ordine);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}
	}

	@Override
	public List<Ordine> getAllOrdini() throws ServiceException {
		List<Ordine> result;

		try {
			result = repository.findAll();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public Ordine getOrdine(Integer id) throws ServiceException {
		Ordine ordine;

		try {
			ordine = repository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return ordine;
	}

	@Override
	public void updateOrdine(Ordine ordine) throws ServiceException {
		if (repository.existsById(ordine.getId())) {
			try {
				repository.save(ordine);
			} catch (Exception e) {
				log.error(TAG + "{}", e);
				throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
			}
		} else {
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		}
	}

	@Override
	public void deleteOrdine(Integer id) throws ServiceException {
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
	public List<Ordine> getOrdineImpiegato(Impiegato impiegato) throws ServiceException {

		List<Ordine> result;

		try {
			result = repository.getOrdineImpiegati(impiegato);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;

	}

}
