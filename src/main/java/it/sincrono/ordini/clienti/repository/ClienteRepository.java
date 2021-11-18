package it.sincrono.ordini.clienti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.ordini.clienti.dto.SpeseCliente;
import it.sincrono.ordini.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, ClienteRepositoryCustom {
	@Query(value = "SELECT new it.sincrono.ordini.clienti.dto.SpeseCliente(a.cliente, SUM(a.importo) AS spesa) FROM Ordine a GROUP BY a.cliente.id ORDER BY spesa DESC")
	public List<SpeseCliente> getSpeseClienti();
}
