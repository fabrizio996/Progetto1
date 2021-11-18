package it.sincrono.ordini.livelli.controller;

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

import it.sincrono.ordini.entities.Livello;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.livelli.dto.LivelloOrdini;
import it.sincrono.ordini.livelli.dto.LivelloSommaImporti;
import it.sincrono.ordini.livelli.service.LivelloService;
import it.sincrono.ordini.responses.GenericListResponse;
import it.sincrono.ordini.responses.GenericObjectResponse;
import it.sincrono.ordini.responses.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LivelloController {
	@Autowired
	private LivelloService service;

	@RequestMapping(value = "/livello/numero-ordini", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<LivelloOrdini>> getLivelloOrdini() {
		HttpEntity<GenericListResponse<LivelloOrdini>> entity;
		GenericListResponse<LivelloOrdini> response = new GenericListResponse<>();

		List<LivelloOrdini> livelloOrdini = null;
		Status status;

		try {
			log.info("START invocation getLivelloOrdini() of controller layer");

			livelloOrdini = service.getLivelloOrdini();
			status = new Status(0, "success");

			log.info("END invocation getLivelloOrdini() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(livelloOrdini);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}
	
	@RequestMapping(value = "/livello/somma-importi", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<LivelloSommaImporti>> getLivelloSommaImporti() {
		HttpEntity<GenericListResponse<LivelloSommaImporti>> entity;
		GenericListResponse<LivelloSommaImporti> response = new GenericListResponse<>();

		List<LivelloSommaImporti> livelloSommaImporti = null;
		Status status;

		try {
			log.info("START invocation getLivelloSommaImporti() of controller layer");

			livelloSommaImporti = service.getLivelloSommaImporti();
			status = new Status(0, "success");

			log.info("END invocation getLivelloSommaImporti() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(livelloSommaImporti);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/livelli/insert", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<Status> addLivello(@Valid @RequestBody Livello livello, BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addLivello() of controller layer");

				service.addLivello(livello);
				status = new Status(0, "success");

				log.info("END invocation addLivello() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/livelli", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Livello>> getAllLivelli() {
		HttpEntity<GenericListResponse<Livello>> entity;
		GenericListResponse<Livello> response = new GenericListResponse<>();

		List<Livello> livelli = null;
		Status status;

		try {
			log.info("START invocation getAllLivelli() of controller layer");

			livelli = service.getAllLivelli();
			status = new Status(0, "success");

			log.info("END invocation getAllLivelli() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(livelli);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/livelli/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericObjectResponse<Livello>> getLivello(@PathVariable Integer id) {
		HttpEntity<GenericObjectResponse<Livello>> entity;
		GenericObjectResponse<Livello> response = new GenericObjectResponse<>();

		Livello Livello = null;
		Status status;

		try {
			log.info("START invocation getLivello() of controller layer");

			Livello = service.getLivello(id);
			status = new Status(0, "success");

			log.info("END invocation getLivello() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setObject(Livello);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/livelli/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody HttpEntity<Status> updateLivello(@Valid @RequestBody Livello Livello, BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addLivello() of controller layer");

				service.updateLivello(Livello);
				status = new Status(0, "success");

				log.info("END invocation addLivello() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/livelli/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody HttpEntity<Status> deleteLivello(@PathVariable Integer id) {
		HttpEntity<Status> entity;

		Status status;

		try {
			log.info("START invocation updateLivello() of controller layer");

			service.deleteLivello(id);
			status = new Status(0, "success");

			log.info("END invocation updateLivello() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		entity = new HttpEntity<>(status);

		return entity;
	}
}
