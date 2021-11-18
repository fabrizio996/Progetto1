package it.sincrono.ordini.handlers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import it.sincrono.ordini.exceptions.ServiceErrors;
import it.sincrono.ordini.responses.Status;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public HttpEntity<Status> handleErrors(MethodArgumentTypeMismatchException exception) {
		HttpEntity<Status> entity;

		Status status = new Status(-3,
				ServiceErrors.inputTypeMismatch(exception.getRequiredType(), exception.getValue().getClass()));

		entity = new HttpEntity<>(status);

		return entity;
	}

	@ExceptionHandler(InvalidFormatException.class)
	public HttpEntity<Status> handleErrors(InvalidFormatException exception) {
		HttpEntity<Status> entity;

		Status status = new Status(-3,
				ServiceErrors.inputTypeMismatch(exception.getTargetType(), exception.getValue().getClass()));

		entity = new HttpEntity<>(status);

		return entity;
	}
}
