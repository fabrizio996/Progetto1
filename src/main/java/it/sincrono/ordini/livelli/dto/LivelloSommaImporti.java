package it.sincrono.ordini.livelli.dto;

import it.sincrono.ordini.entities.Livello;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivelloSommaImporti {
	private Livello livello;
	private Double sommaImporti;
	}

