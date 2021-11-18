package it.sincrono.ordini.impiegati.dto;

import it.sincrono.ordini.entities.Impiegato;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImpiegatiImporto {
	private Impiegato impiegato;
	private Double importoTotale;
}
