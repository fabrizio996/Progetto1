package it.sincrono.ordini.exceptions;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	protected Integer code = null;

	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param code
	 * @param message
	 */
	public ServiceException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
	}
}