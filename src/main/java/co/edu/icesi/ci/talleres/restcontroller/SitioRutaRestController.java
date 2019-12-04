package co.edu.icesi.ci.talleres.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;
import co.edu.icesi.ci.talleres.services.SitioService;
import co.edu.icesi.ci.talleres.services.SitiorutaService;

@RestController
public class SitioRutaRestController {
SitiorutaService sitioService;
	
	@Autowired
	public SitioRutaRestController(SitiorutaService sitioService) {
		this.sitioService= sitioService;
	}
	@GetMapping("api/sitioruta/all")
	public List<Tmio1SitiosRuta> findAll() {
		
		return sitioService.findAll();
	}
	@GetMapping("api/sitioruta/{id}")
	public Tmio1SitiosRuta findById(@PathVariable("id")String id) {
		
		return sitioService.findById(id);
	}
	@PostMapping("api/sitioruta")
	public Tmio1SitiosRuta saveSitio(@RequestBody Tmio1SitiosRuta sitio) {
		sitioService.saveSitio(sitio);
		return sitio;
	}
	@PatchMapping("api/sitioruta/e")
	public Tmio1SitiosRuta updateSitio(@RequestBody Tmio1SitiosRuta sitio) {
		sitioService.updateSitio(sitio);
		return sitio;
	}
	
	@DeleteMapping("/api/sitioruta/delete/{id}")
	public void deleteSitio(@PathVariable("id") String id)
    {       
		Tmio1SitiosRuta r= sitioService.findById(id);
		System.out.println(id);
		System.out.println(r);
		sitioService.removeSitio(r);
    }

	
	

}
