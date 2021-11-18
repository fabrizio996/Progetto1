package it.sincrono.ordini.clienti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.sincrono.ordini.entities.Cliente;

public class ClienteRepositoryCustomImpl implements ClienteRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	private final String SQL_SEARCH_CLIENTE = "SELECT a FROM Cliente a WHERE 1 = 1 {0} ORDER BY a.nome ASC";

	@Override
	public List<Cliente> search(Cliente cliente) throws Exception {
		List<Cliente> list = new ArrayList<>();

		try {
			String queryString = SQL_SEARCH_CLIENTE;
			String subString = "";

			if (cliente != null) {
				if (cliente.getNome() != null) {
					subString += "AND a.nome = '" + cliente.getNome() + "' ";
				}

				if (cliente.getCognome() != null) {
					subString += "AND a.cognome = '" + cliente.getCognome() + "' ";
				}
			}

			queryString = queryString.replace("{0}", subString);
			Query query = entityManager.createQuery(queryString);
			list = query.getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}

		return list;
	}

}
