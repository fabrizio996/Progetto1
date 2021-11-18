package it.sincrono.ordini.impiegati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.exceptions.ServiceErrors;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.impiegati.dto.ImpiegatiImporto;
import it.sincrono.ordini.impiegati.dto.ImpiegatiOrdini;
import it.sincrono.ordini.impiegati.repository.ImpiegatoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {
	private static final String TAG = ImpiegatoServiceImpl.class.getName();
	@Autowired
	private ImpiegatoRepository repository;

	@Override
	public List<Impiegato> findImpiegatiByCliente(Cliente cliente) throws ServiceException {
		List<Impiegato> impiegati = null;

		try {
			impiegati = repository.findImpiegatiByCliente(cliente);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return impiegati;
	}

	@Override
	public void addImpiegato(Impiegato impiegato) throws ServiceException {

		try {
			repository.save(impiegato);

		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

	}

	@Override
	public List<Impiegato> getAllImpiegati() throws ServiceException {
		List<Impiegato> result;
		try {
			result = repository.findAll();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public Impiegato getImpiegato(Integer id) throws ServiceException {
		Impiegato impiegato;

		try {
			impiegato = repository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return impiegato;

	}

	@Override
	public void updateImpiegato(Impiegato impiegato) throws ServiceException {
		if (repository.existsById(impiegato.getId())) {
			try {
				repository.save(impiegato);
			} catch (Exception e) {
				log.error(TAG + "{}", e);
				throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
			}
		} else {
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		}

	}

	@Override
	public void deleteImpiegato(Integer id) throws ServiceException {
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
	public List<ImpiegatiImporto> getImpiegatiImporto() throws ServiceException {
		List<ImpiegatiImporto> impiegati;
		try {
			impiegati = repository.getImpiegatiImporto();
		} catch (EmptyResultDataAccessException e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);

		}
		return impiegati;
	}

	@Override
	public List<ImpiegatiOrdini> getImpiegatiOrdini() throws ServiceException {
		List<ImpiegatiOrdini> impiegati;
		try {
			impiegati = repository.getImpiegatiOrdini();
		} catch (EmptyResultDataAccessException e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}
		return impiegati;
	}

	@Override
	public List<Impiegato> search(Impiegato impiegato) throws ServiceException {
		List<Impiegato> impiegati;
		try {
			impiegati = repository.search(impiegato);
		} catch (EmptyResultDataAccessException e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}
		return impiegati;
	}
}
