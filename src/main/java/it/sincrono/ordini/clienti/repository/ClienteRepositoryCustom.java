package it.sincrono.ordini.clienti.repository;

import java.util.List;

import it.sincrono.ordini.entities.Cliente;

public interface ClienteRepositoryCustom {
	public List<Cliente> search(Cliente cliente) throws Exception;
}
