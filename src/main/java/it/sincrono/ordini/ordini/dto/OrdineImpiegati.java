package it.sincrono.ordini.ordini.dto;

import java.util.List;

import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.entities.Ordine;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdineImpiegati {
	private Ordine ordine;
	private List<Impiegato> impiegati;
}
