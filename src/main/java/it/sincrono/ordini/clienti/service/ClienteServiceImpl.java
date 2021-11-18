package it.sincrono.ordini.clienti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.sincrono.ordini.clienti.dto.SpeseCliente;
import it.sincrono.ordini.clienti.repository.ClienteRepository;
import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.exceptions.ServiceErrors;
import it.sincrono.ordini.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	private ClienteRepository repository;

	private static final String TAG = ClienteServiceImpl.class.getName();

	@Override
	public List<SpeseCliente> getSpeseClienti() throws ServiceException {
		List<SpeseCliente> result;

		try {
			result = repository.getSpeseClienti();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public void addCliente(Cliente cliente) throws ServiceException {
		try {
			repository.save(cliente);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}
	}

	@Override
	public List<Cliente> getAllClienti() throws ServiceException {
		List<Cliente> result;

		try {
			result = repository.findAll();
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}

	@Override
	public Cliente getCliente(Integer id) throws ServiceException {
		Cliente cliente;

		try {
			cliente = repository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return cliente;
	}

	@Override
	public void updateCliente(Cliente cliente) throws ServiceException {
		if (repository.existsById(cliente.getId())) {
			try {
				repository.save(cliente);
			} catch (Exception e) {
				log.error(TAG + "{}", e);
				throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
			}
		} else {
			throw new ServiceException(-2, ServiceErrors.EMPTY_RESULT_DATA_ACCESS_EXCEPTION);
		}
	}

	@Override
	public void deleteCliente(Integer id) throws ServiceException {
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
	public List<Cliente> search(Cliente cliente) throws ServiceException {
		List<Cliente> result;

		try {
			result = repository.search(cliente);
		} catch (Exception e) {
			log.error(TAG + "{}", e);
			throw new ServiceException(-1, ServiceErrors.GENERIC_ERROR);
		}

		return result;
	}
}
