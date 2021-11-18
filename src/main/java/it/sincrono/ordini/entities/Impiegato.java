package it.sincrono.ordini.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.sincrono.ordini.exceptions.ServiceErrors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "impiegati")
public class Impiegato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "nome " + ServiceErrors.REQUIRED_FIELD)
	@Column(name = "nome")
	private String nome;

	@NotBlank(message = "cognome " + ServiceErrors.REQUIRED_FIELD)
	@Column(name = "cognome")
	private String cognome;
	
	@ManyToOne
	@NotNull(message = "livello " + ServiceErrors.REQUIRED_FIELD)
	@JoinColumn(name = "id_livello")
	private Livello livello;
}
