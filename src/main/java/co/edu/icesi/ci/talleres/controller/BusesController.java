package co.edu.icesi.ci.talleres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.talleres.delegate.BusesDelegateImpl;
import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;


@Controller
public class BusesController {

	BusesDelegateImpl busesDelegate;
	
	@Autowired
	public BusesController(BusesDelegateImpl busesDelegate) {
		this.busesDelegate = busesDelegate;
		;
	}

    
	@GetMapping("/buses/")
	public String indexBus(Model model) {
		model.addAttribute("buses", busesDelegate.findAll());
		return "buses/index";
	}
	
	@GetMapping("/buses/add1")
	public String addBus1(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		model.addAttribute("types", BusType.values());
		return "buses/add-buses2";
	}
	
	@PostMapping("/buses/add2")
	public String saveBus2(@Validated Tmio1Bus tmio1Bus, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("types", BusType.values());
				return "buses/add-buses2";
			} else {
				try {
					System.out.println("alcanza a entrar");
					busesDelegate.saveBus(tmio1Bus);
				} catch (Exception e) {
					model.addAttribute("error", new Error(e.getMessage()));
					return "redirect:/error/"; 
				}
			}
		return "redirect:/buses/";
	}
	
	@GetMapping("/buses/del/{id}")
	public String borrarBus(@PathVariable("id") Integer id) throws Exception {
		Tmio1Bus bus = busesDelegate.findById(id);
		busesDelegate.delete(bus);;
		return "redirect:/buses/";
	}
	
	@PostMapping("/buses/search/")
	public String searchBus(Tmio1Bus bus, Model modelo) {
		try {
			Tmio1Bus bus1 = busesDelegate.findById(bus.getId());
			modelo.addAttribute("buses", bus1);
		} catch (Exception s) {
			modelo.addAttribute("buses", busesDelegate.findAll());
		}
		modelo.addAttribute("busSearched", new Tmio1Bus());
		return "buses/index";
	}
	
}
