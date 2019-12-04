package co.edu.icesi.ci.talleres.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.services.SitioService;

@RestController
public class SitioRestController {
	SitioService sitioService;
	
	@Autowired
	public SitioRestController(SitioService sitioService) {
		this.sitioService= sitioService;
	}
	@GetMapping("api/sitio")
	public Iterable<Tmio1Sitio> findAll() {
		
		return sitioService.findAll();
	}
	@GetMapping("api/sitio/{id}")
	public Tmio1Sitio findById(@PathVariable("id")long id) {
		
		return sitioService.findById(id);
	}
	@PostMapping("api/sitio")
	public Tmio1Sitio saveSitio(@RequestBody Tmio1Sitio sitio) {
		sitioService.saveSitio(sitio);
		return sitio;
	}
	@PatchMapping("api/sitio/e")
	public Tmio1Sitio updateSitio(@RequestBody Tmio1Sitio sitio) {
		sitioService.updateSitio(sitio);
		return sitio;
	}
	
	@DeleteMapping("/api/sitio/delete/{id}")
	public void deleteSitio(@PathVariable("id") int id)
    {       
		Tmio1Sitio r= sitioService.findById(id);
		System.out.println(id);
		System.out.println(r);
		sitioService.removeSitio(r);
    }

	
	
	
}
