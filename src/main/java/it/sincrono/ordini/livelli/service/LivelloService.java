package it.sincrono.ordini.livelli.service;

import java.util.List;

import it.sincrono.ordini.entities.Livello;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.livelli.dto.LivelloOrdini;
import it.sincrono.ordini.livelli.dto.LivelloSommaImporti;

public interface LivelloService {

	public List<LivelloOrdini> getLivelloOrdini() throws ServiceException;

	public List<LivelloSommaImporti> getLivelloSommaImporti() throws ServiceException;

	public void addLivello(Livello livello) throws ServiceException;

	public List<Livello> getAllLivelli() throws ServiceException;

	public Livello getLivello(Integer id) throws ServiceException;

	public void updateLivello(Livello Livello) throws ServiceException;

	public void deleteLivello(Integer id) throws ServiceException;

}
