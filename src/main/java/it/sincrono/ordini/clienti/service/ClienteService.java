package it.sincrono.ordini.clienti.service;

import java.util.List;

import it.sincrono.ordini.clienti.dto.SpeseCliente;
import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.exceptions.ServiceException;

public interface ClienteService {
	public List<SpeseCliente> getSpeseClienti() throws ServiceException;

	public void addCliente(Cliente cliente) throws ServiceException;

	public List<Cliente> getAllClienti() throws ServiceException;
	
	public List<Cliente> search(Cliente cliente) throws ServiceException;

	public Cliente getCliente(Integer id) throws ServiceException;

	public void updateCliente(Cliente cliente) throws ServiceException;

	public void deleteCliente(Integer id) throws ServiceException;
}
