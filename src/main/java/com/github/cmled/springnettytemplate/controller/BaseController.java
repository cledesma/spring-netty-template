package com.github.cmled.springnettytemplate.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cmled.springnettytemplate.service.GenericService;

public abstract class BaseController<E> {
	
	protected static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	private GenericService<E> service;

	/*
	 * Instantiate service first!
	 * Note: instantiating generic service from child class is a must
	 */
	protected void initGenericService(Class<E> entityType){
		service = new GenericService<E>(entityType);
	}
	
	private GenericService<E> getService() {
		if (service == null) {
			//TODO Throw exception!
		}
		return service;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void create(E entity) {
		getService().create(entity);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public E update(E entity) {
		return getService().update(entity);
	}

	@DELETE
	@Path("/{id: \\d+}")
	public void delete(@PathParam("id") final long id) {
		getService().delete(id);
	}

	@GET
	@Path("/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public E findById(@PathParam("id") final long id) {
		return getService().findById(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> findAll() {
		return getService().findAll();
	}

}
