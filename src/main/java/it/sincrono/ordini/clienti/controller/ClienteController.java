package it.sincrono.ordini.clienti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.ordini.clienti.dto.SpeseCliente;
import it.sincrono.ordini.clienti.service.ClienteService;
import it.sincrono.ordini.entities.Cliente;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.responses.GenericListResponse;
import it.sincrono.ordini.responses.GenericObjectResponse;
import it.sincrono.ordini.responses.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ClienteController {
	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/clienti/spese", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<SpeseCliente>> getSpeseClienti() {
		HttpEntity<GenericListResponse<SpeseCliente>> entity;
		GenericListResponse<SpeseCliente> response = new GenericListResponse<>();

		List<SpeseCliente> speseClienti = null;
		Status status;

		try {
			log.info("START invocation getSpeseClienti() of controller layer");

			speseClienti = service.getSpeseClienti();
			status = new Status(0, "success");

			log.info("END invocation getSpeseClienti() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(speseClienti);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/clienti/insert", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<Status> addCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addCliente() of controller layer");

				service.addCliente(cliente);
				status = new Status(0, "success");

				log.info("END invocation addCliente() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/clienti", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Cliente>> getAllClienti() {
		HttpEntity<GenericListResponse<Cliente>> entity;
		GenericListResponse<Cliente> response = new GenericListResponse<>();

		List<Cliente> clienti = null;
		Status status;

		try {
			log.info("START invocation getAllClienti() of controller layer");

			clienti = service.getAllClienti();
			status = new Status(0, "success");

			log.info("END invocation getAllClienti() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(clienti);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/clienti/cliente", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Cliente>> getAllClienti(@RequestBody Cliente cliente) {
		HttpEntity<GenericListResponse<Cliente>> entity;
		GenericListResponse<Cliente> response = new GenericListResponse<>();

		List<Cliente> impiegati = null;
		Status status;

		try {
			log.info("START invocation getAllClienti() of controller layer");

			impiegati = service.search(cliente);
			status = new Status(0, "success");

			log.info("END invocation getAllClienti() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(impiegati);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/clienti/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericObjectResponse<Cliente>> getCliente(@PathVariable Integer id) {
		HttpEntity<GenericObjectResponse<Cliente>> entity;
		GenericObjectResponse<Cliente> response = new GenericObjectResponse<>();

		Cliente cliente = null;
		Status status;

		try {
			log.info("START invocation getCliente() of controller layer");

			cliente = service.getCliente(id);
			status = new Status(0, "success");

			log.info("END invocation getCliente() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setObject(cliente);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/clienti/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody HttpEntity<Status> updateCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addCliente() of controller layer");

				service.updateCliente(cliente);
				status = new Status(0, "success");

				log.info("END invocation addCliente() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/clienti/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody HttpEntity<Status> deleteCliente(@PathVariable Integer id) {
		HttpEntity<Status> entity;

		Status status;

		try {
			log.info("START invocation updateCliente() of controller layer");

			service.deleteCliente(id);
			status = new Status(0, "success");

			log.info("END invocation updateCliente() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		entity = new HttpEntity<>(status);

		return entity;
	}
}
