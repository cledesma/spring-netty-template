package com.github.cmled.springnettytemplate.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
@Path("/staticfiles")
public class StaticFilesController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StaticFilesController.class);
	
	/*
	 * Serve static files
	 */

	@GET
	@Path("/public/html/{fileName}")
	public File getHtml(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/html/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
	
	@GET
	@Path("/public/img/{fileName}")
	public File getImage(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/img/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
	
	@GET
	@Path("/public/js/{fileName}")
	public File getJs(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/js/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
	
	@GET
	@Path("/public/css/{fileName}")
	public File getCss(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/css/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
}
