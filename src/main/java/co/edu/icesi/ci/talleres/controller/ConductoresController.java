package co.edu.icesi.ci.talleres.controller;

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

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.services.ConductorService;


@Controller
public class ConductoresController {

	ConductorService conductorService;
	
	@Autowired
	public ConductoresController(ConductorService conductorService) {
		this.conductorService = conductorService;
		;
	}
    
	@GetMapping("/conductores/")
	public String indexConductor(Model model) {
		model.addAttribute("conductores", conductorService.findAll());
		return "conductores/index";
	}
	
	@GetMapping("/conductores/add1")
	public String addConductor(Model model) {
		model.addAttribute("driver", new Tmio1Conductore());
		return "/conductores/add-conductor";
	}
	
	@PostMapping("/conductores/add1")
	public String saveConductor(@ModelAttribute("driver") @Valid Tmio1Conductore driver, BindingResult bindingResult,@RequestParam(value = "action", required = true) String action , Model model) {
		if (!action.equals("Cancel")) {
		if (bindingResult.hasErrors()) {
				return "/conductores/add-conductor";
			} else {
				try {
					conductorService.saveConductor(driver);
				} catch (Exception e) {
					model.addAttribute("error", new Error(e.getMessage()));
					return "redirect:/error/"; 
				}
			}
		}
		return "redirect:/conductores/";
	}
	
}
