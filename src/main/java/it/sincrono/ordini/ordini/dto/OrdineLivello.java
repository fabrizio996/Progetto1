package it.sincrono.ordini.ordini.dto;

import it.sincrono.ordini.entities.Livello;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdineLivello {
	private Livello livello;
	private Integer numeroOrdini;
}
