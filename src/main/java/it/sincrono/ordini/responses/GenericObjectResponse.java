package it.sincrono.ordini.responses;

import lombok.Data;

@Data
public class GenericObjectResponse<T> {
	private T object;
	private Status status;
}
