
package it.sincrono.ordini.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
	private int code;
	private String description;
}
