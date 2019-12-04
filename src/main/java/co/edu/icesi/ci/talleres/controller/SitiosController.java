package co.edu.icesi.ci.talleres.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.talleres.delegate.SitioDelegate;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.services.BusService;
@Controller
public class SitiosController {
	SitioDelegate sitioDelegate;
	@Autowired
	public SitiosController(SitioDelegate sitioService){
		this.sitioDelegate= sitioService;
	}
	@GetMapping("/Sitio")
	public String index(Model model) {
		model.addAttribute("sitios",sitioDelegate.findAll());
		return "/Sitio/index";
	}
	@GetMapping("/Sitio/add-Sitio")
	public String addSitio(Model model){
		model.addAttribute("sitio",new Tmio1Sitio());
		return "/Sitio/add-sitio";
	}
	@PostMapping("/Sitio/add-Sitio/")
	public String saveSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/Sitio/add-Sitio";
		}
		sitioDelegate.saveSitio(sitio);
		return "redirect:/Sitio/";
	}
	@GetMapping("/Sitio/edit/{id}")
	public String editSitio(@PathVariable("id") int id,Model model){
		model.addAttribute("Sitio",sitioDelegate.findById(id));	
		return "/Sitio/edit-sitio";
	}
	@GetMapping("/Sitio/delete/{id}")
	public String deleteSitio(@PathVariable("id") String id) {
		sitioDelegate.removeSitio(id);
		return "redirect:/sitio/";
	}
	@PostMapping("/Sitio/edit/")
	public String editSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/Sitio/edit-sitio";
		}
		System.out.println(sitio.getId());
		sitioDelegate.updateSitio(sitio);
		return "redirect:/Sitio/";
	}

}
