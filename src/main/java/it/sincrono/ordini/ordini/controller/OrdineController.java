package it.sincrono.ordini.ordini.controller;

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

import it.sincrono.ordini.entities.Impiegato;
import it.sincrono.ordini.entities.Ordine;
import it.sincrono.ordini.exceptions.ServiceException;
import it.sincrono.ordini.ordini.service.OrdineService;
import it.sincrono.ordini.responses.GenericListResponse;
import it.sincrono.ordini.responses.GenericObjectResponse;
import it.sincrono.ordini.responses.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrdineController {
	@Autowired
	private OrdineService service;

	@RequestMapping(value = "/ordini/mese/{mese}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Ordine>> get(@PathVariable("mese") Integer mese ) {
		HttpEntity<GenericListResponse<Ordine>> entity;
		GenericListResponse<Ordine> response = new GenericListResponse<>();

		List<Ordine> ordini = null;
		Status status;

		try {
			log.info("START invocation getOrdineMese() of controller layer");

			ordini = service.getOrdineMese(mese);
			status = new Status(0, "success");

			log.info("END invocation getOrdineMese() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(ordini);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/ordini/insert", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<Status> addOrdine(@Valid @RequestBody Ordine ordine, BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addOrdine() of controller layer");

				service.addOrdine(ordine);
				status = new Status(0, "success");

				log.info("END invocation addOrdine() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/ordini", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Ordine>> getAllOrdini() {
		HttpEntity<GenericListResponse<Ordine>> entity;
		GenericListResponse<Ordine> response = new GenericListResponse<>();

		List<Ordine> ordini = null;
		Status status;

		try {
			log.info("START invocation getAllOrdini() of controller layer");

			ordini = service.getAllOrdini();
			status = new Status(0, "success");

			log.info("END invocation getAllOrdini() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(ordini);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/ordini/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<GenericObjectResponse<Ordine>> getOrdine(@PathVariable Integer id) {
		HttpEntity<GenericObjectResponse<Ordine>> entity;
		GenericObjectResponse<Ordine> response = new GenericObjectResponse<>();

		Ordine ordine = null;
		Status status;

		try {
			log.info("START invocation getOrdine() of controller layer");

			ordine = service.getOrdine(id);
			status = new Status(0, "success");

			log.info("END invocation getOrdine() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setObject(ordine);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
	}

	@RequestMapping(value = "/ordini/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody HttpEntity<Status> updateOrdine(@Valid @RequestBody Ordine ordine, BindingResult result) {
		HttpEntity<Status> entity;

		Status status;

		if (result.hasErrors()) {
			status = new Status(-4, result.getFieldError().getDefaultMessage());
		} else {
			try {
				log.info("START invocation addOrdine() of controller layer");

				service.updateOrdine(ordine);
				status = new Status(0, "success");

				log.info("END invocation addOrdine() of controller layer");
			} catch (ServiceException e) {
				status = new Status(e.getCode(), e.getMessage());
			}
		}

		entity = new HttpEntity<>(status);

		return entity;
	}

	@RequestMapping(value = "/ordini/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody HttpEntity<Status> deleteOrdine(@PathVariable Integer id) {
		HttpEntity<Status> entity;

		Status status;

		try {
			log.info("START invocation updateOrdine() of controller layer");

			service.deleteOrdine(id);
			status = new Status(0, "success");

			log.info("END invocation updateOrdine() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		entity = new HttpEntity<>(status);

		return entity;
	}
	
	@RequestMapping(value = "/ordini/impiegato", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<GenericListResponse<Ordine>> getOrdineImpiegato(@RequestBody Impiegato impiegato) {

		HttpEntity<GenericListResponse<Ordine>> entity;
		GenericListResponse<Ordine> response = new GenericListResponse<>();

		List<Ordine> ordini = null;
		Status status;

		try {
			log.info("START invocation getAllOrdini() of controller layer");

			ordini = service.getOrdineImpiegato(impiegato);
			status = new Status(0, "success");

			log.info("END invocation getAllOrdini() of controller layer");
		} catch (ServiceException e) {
			status = new Status(e.getCode(), e.getMessage());
		}

		response.setList(ordini);
		response.setStatus(status);

		entity = new HttpEntity<>(response);

		return entity;
				
	}
		
}
