package it.sincrono.ordini.impiegati.repository;

import java.util.List;

import it.sincrono.ordini.entities.Impiegato;

public interface ImpiegatoRepositoryCustom {
	public List<Impiegato> search(Impiegato impiegato) throws Exception;
}
