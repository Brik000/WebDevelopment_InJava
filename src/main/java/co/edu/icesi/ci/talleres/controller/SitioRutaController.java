package co.edu.icesi.ci.talleres.controller;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.ci.talleres.services.SitioService;


public class SitioRutaController {
	
	private SitioService SitioService;

	@Autowired
	public SitioRutaController(SitioService busService) {
		this.SitioService = busService;
		;
	}
	
	

}
