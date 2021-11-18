package it.sincrono.ordini.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import it.sincrono.ordini.exceptions.ServiceErrors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordini")
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "data " + ServiceErrors.REQUIRED_FIELD)
	@Column(name = "data")
	private Date data;

	@Column(name = "importo")
	private Double importo;

	@ManyToOne
	@NotNull(message = "id_cliente " + ServiceErrors.REQUIRED_FIELD)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@NotNull(message = "idi_impiegato " + ServiceErrors.REQUIRED_FIELD)
	@JoinColumn(name = "id_impiegato")
	private Impiegato impiegato;
}
