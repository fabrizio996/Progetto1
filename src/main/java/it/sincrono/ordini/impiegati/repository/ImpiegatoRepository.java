package it.sincrono.ordini.impiegati.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.impiegati.dto.ImpiegatiImporto;
import it.sincrono.ordini.impiegati.dto.ImpiegatiOrdini;

public interface ImpiegatoRepository extends JpaRepository<Impiegato, Integer>, ImpiegatoRepositoryCustom {

	// Query 1
	@Query(value = "SELECT a FROM Impiegato a JOIN Ordine b ON a.id = b.impiegato.id JOIN Cliente c ON b.cliente.id = c.id WHERE c.id = :#{#cliente.id}")
	public List<Impiegato> findImpiegatiByCliente(@Param("cliente") Cliente cliente);

	// Query 6
	@Query(value = "SELECT new it.sincrono.ordini.impiegati.dto.ImpiegatiImporto(a.impiegato, SUM(a.importo) AS importo_totale) FROM Ordine a GROUP BY a.impiegato.id")
	public List<ImpiegatiImporto> getImpiegatiImporto();

	// Query 7
	@Query(value = "SELECT new it.sincrono.ordini.impiegati.dto.ImpiegatiOrdini(a.impiegato, COUNT(a.id) AS numero_ordini) FROM Ordine a GROUP BY a.impiegato.id")
	public List<ImpiegatiOrdini> getImpiegatiOrdini();

}
