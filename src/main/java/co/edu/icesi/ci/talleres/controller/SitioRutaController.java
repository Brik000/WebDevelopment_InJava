package co.edu.icesi.ci.talleres.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.ci.talleres.delegate.SitioDelegate;
import co.edu.icesi.ci.talleres.delegate.SitioRutaDelegate;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRutaPK;
import co.edu.icesi.ci.talleres.services.SitioService;

@Controller
public class SitioRutaController {
	
	SitioRutaDelegate sitioDelegate;
	@Autowired
	public SitioRutaController(SitioRutaDelegate sitioService){
		this.sitioDelegate= sitioService;
	}
	@GetMapping("/sitioruta/")
	public String index(Model model) {
		model.addAttribute("sitiosRuta",sitioDelegate.findAll());
		return "/sitioruta/index";
	}
	@GetMapping("/sitioruta/add-sitioRuta")
	public String addSitio(Model model){
		model.addAttribute("tmio1Sitio",new Tmio1SitiosRutaPK());
		model.addAttribute("routes", sitioDelegate.getAllRoutes());	
		model.addAttribute("sitios", sitioDelegate.getAllSitio());
		return "sitioruta/add-Sitioruta";
	}
	@PostMapping("sitioRuta/add-sitioRuta/")
	public String saveSitio(@Valid @ModelAttribute("tmio1Sitio") Tmio1SitiosRutaPK sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitioRuta",sitio);
			return "/sitioruta/add-Sitioruta";
		}
		Tmio1SitiosRuta n= new Tmio1SitiosRuta();
		n.setHash(sitio.hashCode());
		n.setId(sitio);
		
		n.setTmio1Ruta1(sitioDelegate.findRutaByID(sitio.getIdRuta()));
		n.setTmio1Sitio1(sitioDelegate.findSitioByID(sitio.getIdSitio()));
		
		sitioDelegate.saveSitio(n);
		return "redirect:/sitioruta/";
	}
	@GetMapping("/sitioruta/edit/{id}")
	public String editSitio(@PathVariable("id") int id,Model model){
		model.addAttribute("tmio1SitioRuta",sitioDelegate.findById(id));	
		return "/sitioruta/edit-sitio";
	}
	@GetMapping("/sitioruta/delete/{id}")
	public String deleteSitio(@PathVariable("id") String id) {
		sitioDelegate.removeSitio(id);
		return "redirect:/sitioruta/";
	}
	@PostMapping("/sitioruta/edit-sitio/{id}")
	public String editSitio(@PathVariable("id") String id,@Valid @ModelAttribute("sitioRuta") Tmio1SitiosRuta sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitioRuta",sitio);
			return "/sitioruta/edit-sitioruta";
		}
		sitio.setHash(Integer.parseInt(id));
		sitioDelegate.removeSitio(id);
		sitioDelegate.saveSitio(sitio);
		System.out.println(sitio.getId());
		sitioDelegate.updateSitio(sitio);
		return "redirect:/sitioruta/";
	}

}
