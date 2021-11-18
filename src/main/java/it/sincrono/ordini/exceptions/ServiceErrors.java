package it.sincrono.ordini.exceptions;

public class ServiceErrors {
	public static final String GENERIC_ERROR = "Generic Error";
	public static final String EMPTY_RESULT_DATA_ACCESS_EXCEPTION = "No data with that id";
	public static final String INPUT_TYPE_MISMATCH = "Input type mismatch. Needed %s got %s";
	public static final String REQUIRED_FIELD = "field is required";

	public static String inputTypeMismatch(Class<?> needed, Class<?> got) {
		return String.format(INPUT_TYPE_MISMATCH, needed.getName(), got.getName());
	}

	public static String invalidField(String field) {
		return String.format(REQUIRED_FIELD, field);
	}
}
