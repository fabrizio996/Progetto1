package it.sincrono.ordini.responses;

import java.util.List;

import lombok.Data;

@Data
public class GenericListResponse<T> {
	private List<T> list;
	private Status status;
}
