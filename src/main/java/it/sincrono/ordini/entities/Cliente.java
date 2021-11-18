package it.sincrono.ordini.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import it.sincrono.ordini.exceptions.ServiceErrors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clienti")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "nome " + ServiceErrors.REQUIRED_FIELD)
	@Column(name = "nome")
	private String nome;

	@NotBlank(message = "cognome " + ServiceErrors.REQUIRED_FIELD)
	@Column(name = "cognome")
	private String cognome;
}
