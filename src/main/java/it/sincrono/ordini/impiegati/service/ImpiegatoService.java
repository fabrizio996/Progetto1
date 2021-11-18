package it.sincrono.ordini.impiegati.service;

import java.util.List;

import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.impiegati.dto.ImpiegatiImporto;
import it.sincrono.ordini.impiegati.dto.ImpiegatiOrdini;

public interface ImpiegatoService {

	public List<Impiegato> findImpiegatiByCliente(Cliente cliente) throws ServiceException;

	public List<ImpiegatiImporto> getImpiegatiImporto() throws ServiceException;

	public List<ImpiegatiOrdini> getImpiegatiOrdini() throws ServiceException;

	public void addImpiegato(Impiegato impiegato) throws ServiceException;

	public List<Impiegato> getAllImpiegati() throws ServiceException;

	public List<Impiegato> search(Impiegato impiegato) throws ServiceException;

	public Impiegato getImpiegato(Integer id) throws ServiceException;

	public void updateImpiegato(Impiegato impiegato) throws ServiceException;

	public void deleteImpiegato(Integer id) throws ServiceException;

}
