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

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.services.ServiceService;


@Controller
public class ServiceController{
	ServiceService service;

	@Autowired
	public ServiceController(ServiceService service) {
		this.service= service;
		;
	}
    
	@GetMapping("/services/")
	public String indexUser(Model model) {
		model.addAttribute("services", service.findAllServices());
		model.addAttribute("dateNew", LocalDate.now());
		return "services/index";
	}
	
	@GetMapping("/services/add")
	public String addService(Model model){
		model.addAttribute("buses", service.findAllBuses());
		model.addAttribute("routes",service.findAllRoutes());
		model.addAttribute("drivers",service.findAllDrivers());
		model.addAttribute("service", new Tmio1ServicioPK());
		
		return "/services/addservice";
	}
	
	@PostMapping("/services/add1")
	public String saveService(@ModelAttribute("service") @Valid Tmio1ServicioPK servicio, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()) {
			model.addAttribute("buses", service.findAllBuses());
			model.addAttribute("routes",service.findAllRoutes());
			model.addAttribute("drivers",service.findAllDrivers());
			model.addAttribute("service", new Tmio1ServicioPK());
			return "/services/addservice";
		}
		else {
			service.saveService(servicio);
		}		
		return "redirect:/services/";
	}
	

	@GetMapping("/services/edit/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {
		Optional<Tmio1ServicioPK> services = service.findPKId(id);
		if (services == null)
			throw new IllegalArgumentException("Invalid service Id:" + id);
		model.addAttribute("service", services.get());
		model.addAttribute("buses", service.findAllBuses());
		model.addAttribute("routes",service.findAllRoutes());
		model.addAttribute("drivers",service.findAllDrivers());
		model.addAttribute("path",id);
		return "services/editservice";
	}

	
	
	@PostMapping("/services/edit2/{id}")
	public String updateService(@PathVariable("id") String id,@ModelAttribute("service") @Valid Tmio1ServicioPK servicioNew,BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("buses", service.findAllBuses());
				model.addAttribute("routes",service.findAllRoutes());
				model.addAttribute("drivers",service.findAllDrivers());
				return "/services/edit/";
			}
			
			Optional<Tmio1Servicio> serv = service.findById(service.findPKId(id).get());
			if (serv == null)
				throw new IllegalArgumentException("Invalid service Id");
			
			serv.get().setTmio1Conductore(service.findByDriverId(servicioNew.getCedulaConductor()).get());
			serv.get().setTmio1Bus(service.findByBusId(servicioNew.getIdBus()).get());
			serv.get().setTmio1Ruta(service.findByRouteId(servicioNew.getIdRuta()).get());
			serv.get().getId().setIdBus(servicioNew.getIdBus());
			serv.get().getId().setCedulaConductor(servicioNew.getCedulaConductor());
			serv.get().getId().setIdRuta(servicioNew.getIdRuta());
			serv.get().getId().setFechaFin(servicioNew.getFechaFin());
			serv.get().getId().setFechaInicio(servicioNew.getFechaInicio());
			
			service.saveService2(serv.get());
		}
		return "redirect:/services/";
	}
	
	
	@GetMapping("/services/filtrar")
	public String showFilterForm(Model model) {
		model.addAttribute("service", new Tmio1ServicioPK());
		return "services/filter";
	}

	
	
	@PostMapping("/services/filter1/")
	public String FiltrarService(@ModelAttribute("service") @Valid Tmio1ServicioPK servicioNew,BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("service", new Tmio1ServicioPK());
				return "/services/filter/";
			}
			model.addAttribute("services", service.filtrar(servicioNew.getFechaInicio()));
		}
		return "services/index";
	}
	

	
}
