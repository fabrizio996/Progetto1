package it.sincrono.ordini.clienti.dto;

import it.sincrono.ordini.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpeseCliente {
	private Cliente cliente;
	private Double spesa;
}
