package it.sincrono.ordini.livelli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.ordini.entities.Livello;
import it.sincrono.ordini.livelli.dto.LivelloOrdini;
import it.sincrono.ordini.livelli.dto.LivelloSommaImporti;

public interface LivelloRepository extends JpaRepository<Livello, Integer> {
	// Query 4
	@Query(value = "SELECT new it.sincrono.ordini.livelli.dto.LivelloOrdini(a.impiegato.livello, COUNT(a.id) AS numero_ordini) FROM Ordine a GROUP BY a.impiegato.livello.id")
	public List<LivelloOrdini> getLivelloOrdini();

	// Query 8
	@Query(value = "SELECT new it.sincrono.ordini.livelli.dto.LivelloSommaImporti(a.impiegato.livello, SUM(a.importo) AS somma_importi) FROM Ordine a GROUP BY a.impiegato.livello.id")
	public List<LivelloSommaImporti> getLivelloSommaImporti();
}
