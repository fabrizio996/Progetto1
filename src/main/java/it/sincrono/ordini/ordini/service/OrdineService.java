package it.sincrono.ordini.ordini.service;

import java.util.List;

import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.entities.Ordine;
import it.sincrono.ordini.exceptions.ServiceException;

public interface OrdineService {
	public List<Ordine> getOrdineMese(Integer mese) throws ServiceException;

	public void addOrdine(Ordine ordine) throws ServiceException;

	public List<Ordine> getAllOrdini() throws ServiceException;

	public Ordine getOrdine(Integer id) throws ServiceException;

	public void updateOrdine(Ordine ordine) throws ServiceException;

	public void deleteOrdine(Integer id) throws ServiceException;

	public List<Ordine> getOrdineImpiegato(Impiegato impiegato) throws ServiceException;

}
