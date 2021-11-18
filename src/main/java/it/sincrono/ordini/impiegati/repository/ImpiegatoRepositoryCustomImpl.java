package it.sincrono.ordini.impiegati.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.sincrono.ordini.entities.Impiegato;

public class ImpiegatoRepositoryCustomImpl implements ImpiegatoRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	private final String SQL_SEARCH_IMPIEGATO = "SELECT a FROM Impiegato a WHERE 1 = 1 {0} ORDER BY a.nome ASC";

	@Override
	public List<Impiegato> search(Impiegato impiegato) throws Exception {
		List<Impiegato> list = new ArrayList<Impiegato>();

		try {
			String queryString = SQL_SEARCH_IMPIEGATO;
			String subString = "";

			if (impiegato != null) {
				if (impiegato.getNome() != null) {
					subString += "AND a.nome = '" + impiegato.getNome() + "' ";
				}

				if (impiegato.getCognome() != null) {
					subString += "AND a.cognome = '" + impiegato.getCognome() + "' ";
				}

				if (impiegato.getLivello() != null) {
					subString += "AND a.livello.id = '" + impiegato.getLivello().getId() + "' ";
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
