package co.edu.icesi.ci.talleres.controller;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.talleres.delegate.ServiceDelegateImpl;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.services.ServiceService;


@Controller
public class ServiceController{
	
	ServiceDelegateImpl service;

	@Autowired
	public ServiceController(ServiceDelegateImpl service) {
		this.service= service;
		;
	}
    
	@GetMapping("services")
	public String indexUser(Model model) {
		model.addAttribute("services", service.findAllServices());
		model.addAttribute("dateNew", LocalDate.now());
		return "services/index";
	}
	
	@GetMapping("/services/add1")
	public String addService(Model model){
		model.addAttribute("buses", service.findAllBuses());
		model.addAttribute("routes",service.findAllRoutes());
		model.addAttribute("drivers",service.findAllDrivers());
		model.addAttribute("service", new Tmio1ServicioPK());
		
		return "/services/add-service2";
	}
	
	@PostMapping("/services/add2")
	public String saveService(@ModelAttribute("service") @Valid Tmio1ServicioPK servicio, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()) {
			model.addAttribute("buses", service.findAllBuses());
			model.addAttribute("routes",service.findAllRoutes());
			model.addAttribute("drivers",service.findAllDrivers());
			model.addAttribute("service", new Tmio1ServicioPK());
			return "/services/add-service2";
		}
		else {
			service.saveService(servicio);
		}		
		return "redirect:/services/";
	}
	
	private String tempHash = "";
	@PostMapping("/service/edit/{id}")
	public String saveUpdateService(@PathVariable("id") String hash, Tmio1ServicioPK nuevo, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("buses", service.findAllBuses());
			model.addAttribute("routes", service.findAllRoutes());
			model.addAttribute("drivers", service.findAllDrivers());
			tempHash = hash;
			model.addAttribute("tempHash", tempHash);

			return "/service/update-service";
		}

		Tmio1Bus bus = service.findByBusId(nuevo.getIdBus());
		Tmio1Conductore driver = service.findByDriverId(nuevo.getCedulaConductor());
		Tmio1Ruta route = service.findByRouteId(nuevo.getIdRuta());

		Tmio1Servicio s = new Tmio1Servicio();
		s.setId(nuevo);
		s.setTmio1Bus(bus);
		s.setTmio1Conductore(driver);
		s.setTmio1Ruta(route);
		s.setHash(s.getId().getHashId());

		service.editService(s);
		service.delete(tempHash);

		return "redirect:/service/";
	}

	@GetMapping("/services/filtrar")
	public String showFilterForm(Model model) {
		model.addAttribute("service", new Tmio1ServicioPK());
		return "services/Filtrar";
	}

	
	
	@PostMapping("/services/filter1/")
	public String FiltrarService(@ModelAttribute("service") @Valid Tmio1ServicioPK servicioNew,BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("service", new Tmio1ServicioPK());
				return "/services/Filtrar/";
			}
			model.addAttribute("services", service.filtrar(servicioNew.getFechaInicio()));
		}
		return "services/index";
	}
	
	
}