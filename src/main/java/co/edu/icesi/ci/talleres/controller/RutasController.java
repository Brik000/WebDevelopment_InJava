package co.edu.icesi.ci.talleres.controller;


import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import co.edu.icesi.ci.talleres.delegate.RutasDelegateImpl;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;



@Controller
public class RutasController {
	
	RutasDelegateImpl rutaDelegate;

	@Autowired
	public RutasController(RutasDelegateImpl rutaDelegate) {
		this.rutaDelegate= rutaDelegate;
		;
	}
    
	@GetMapping("/rutas/")
	public String indexUser(Model model) {
		model.addAttribute("rutas", rutaDelegate.findAll());
		model.addAttribute("dateNew", LocalDate.now());
		return "rutas/index";
	}
	
	
	@GetMapping("/rutas/add1")
	public String addRutas1(Model model) {
		model.addAttribute("Tmio1Ruta", new Tmio1Ruta());
		return "rutas/add-ruta2";
	}
	
	@PostMapping("/rutas/add2")
	public String saveRuta2(@ModelAttribute("Tmio1Ruta")@Valid Tmio1Ruta ruta, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				return "rutas/add-ruta2";
			} else {
				rutaDelegate.saveRuta(ruta);
			}
		return "redirect:/rutas/";
	}
	
}
