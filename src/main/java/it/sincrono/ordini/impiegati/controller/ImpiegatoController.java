package it.sincrono.ordini.impiegati.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.exceptions.ServiceErrors;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.impiegati.dto.ImpiegatiImporto;
import it.sincrono.ordini.impiegati.dto.ImpiegatiOrdini;
import it.sincrono.ordini.impiegati.service.ImpiegatoService;
import it.sincrono.ordini.responses.GenericListResponse;
import it.sincrono.ordini.responses.GenericObjectResponse;
import it.sincrono.ordini.responses.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ImpiegatoController {
	@Autowired
	private ImpiegatoService service;

	@RequestMapping(value = "/impiegati/cliente", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Impiegato>> findImpiegatiByCliente(
			@Valid @RequestBody Cliente cliente, BindingResult result) {
		HttpEntity<GenericListResponse<Impiegato>> entity;
		GenericListResponse<Impiegato> response = new GenericListResponse<>();

		List<Impiegato> impiegati = null;
		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation findImpiegatiByCliente() of controller layer");

				impiegati = service.findImpiegatiByCliente(cliente);
				status = new Status(0, "success");

				log.info("END invocation findImpiegatiByCliente() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		response.setList(impiegati);
		response.setStatus(status);
		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/impiegati/insert", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<Status> addImpiegato(@Valid @RequestBody Impiegato impiegato,
			BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation getImpiegati() of controller layer");

				service.addImpiegato(impiegato);
				status = new Status(0, "success");

				log.info("END invocation getImpiegati() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/impiegati", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Impiegato>> getAllImpiegati() {
		HttpEntity<GenericListResponse<Impiegato>> entity;
		GenericListResponse<Impiegato> response = new GenericListResponse<>();

		List<Impiegato> impiegati = null;
		Status status;

		try {
			log.info("START invocation getAllImpiegati() of controller layer");

			impiegati = service.getAllImpiegati();
			status = new Status(0, "success");

			log.info("END invocation getAllImpiegati() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(impiegati);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}
	
	@RequestMapping(value = "/impiegati/impiegato", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Impiegato>> getAllImpiegati(@RequestBody Impiegato impiegato) {
		HttpEntity<GenericListResponse<Impiegato>> entity;
		GenericListResponse<Impiegato> response = new GenericListResponse<>();

		List<Impiegato> impiegati = null;
		Status status;

		try {
			log.info("START invocation getAllImpiegati() of controller layer");

			impiegati = service.search(impiegato);
			status = new Status(0, "success");

			log.info("END invocation getAllImpiegati() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(impiegati);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/impiegati/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericObjectResponse<Impiegato>> getImpiegato(@PathVariable Integer id) {
		HttpEntity<GenericObjectResponse<Impiegato>> entity;
		GenericObjectResponse<Impiegato> response = new GenericObjectResponse<>();

		Impiegato impiegato = null;
		Status status;

		try {
			log.info("START invocation getImpiegato() of controller layer");

			impiegato = service.getImpiegato(id);
			status = new Status(0, "success");

			log.info("END invocation getImpiegato() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setObject(impiegato);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/impiegati/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody HttpEntity<Status> updateImpiegato(@Valid @RequestBody Impiegato impiegato,
			BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addImpiegato() of controller layer");

				service.updateImpiegato(impiegato);
				status = new Status(0, "success");

				log.info("END invocation addImpiegato() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/impiegati/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody HttpEntity<Status> deleteImpiegato(@PathVariable Integer id) {
		HttpEntity<Status> entity;

		Status status;

		try {
			log.info("START invocation updateImpiegato() of controller layer");

			service.deleteImpiegato(id);
			status = new Status(0, "success");

			log.info("END invocation updateImpiegato() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/impiegati/importo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<ImpiegatiImporto>> getImpiegatiImporto() {
		HttpEntity<GenericListResponse<ImpiegatiImporto>> entity;
		GenericListResponse<ImpiegatiImporto> response = new GenericListResponse<>();

		List<ImpiegatiImporto> impiegati = null;
		Status status;

		try {
			log.info("START invocation updateImpiegato() of controller layer");

			impiegati = service.getImpiegatiImporto();
			status = new Status(0, "success");

			log.info("END invocation updateImpiegato() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(impiegati);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/impiegati/ordine", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<ImpiegatiOrdini>> getImpiegatiOrdini() {
		HttpEntity<GenericListResponse<ImpiegatiOrdini>> entity;
		GenericListResponse<ImpiegatiOrdini> response = new GenericListResponse<>();

		List<ImpiegatiOrdini> impiegati = null;
		Status status;

		try {
			log.info("START invocation updateImpiegato() of controller layer");

			impiegati = service.getImpiegatiOrdini();
			status = new Status(0, "success");

			log.info("END invocation updateImpiegato() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(impiegati);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

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
