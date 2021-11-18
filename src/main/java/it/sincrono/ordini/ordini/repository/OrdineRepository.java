package it.sincrono.ordini.ordini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.entities.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

	// Query 5
	@Query(value = "select a from Ordine a  where month(a.data)=:mese")
	public List<Ordine> getOrdineMese(@Param("mese") Integer mese);

	// Query 2
	@Query(value = "SELECT a FROM Ordine a WHERE a.impiegato.id = :#{#impiegato.id}")
	public List<Ordine> getOrdineImpiegati(@Param("impiegato") Impiegato impiegato);

}