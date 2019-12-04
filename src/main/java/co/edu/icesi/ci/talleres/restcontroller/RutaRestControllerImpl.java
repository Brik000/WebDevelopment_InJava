package co.edu.icesi.ci.talleres.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.services.RutaService;

@RestController
public class RutaRestControllerImpl implements RutaRestController {
	
	@Autowired
	private RutaService service;
	

	@Override
	@PostMapping("api/rutas")
	public Tmio1Ruta saveRuta(@RequestBody Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		service.saveRuta(ruta);
		return ruta;
	}

	@Override
	@PostMapping("api/rutas/validar")
	public Tmio1Ruta validarRuta(Tmio1Ruta ruta) throws Exception {
		// TODO Auto-generated method stub
		service.validarRuta(ruta);
		return ruta;
	}

	@Override
	@GetMapping("api/rutas/{id}")
	public Optional<Tmio1Ruta> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ENTROOOOOOOOO");
		return service.findById(id);
	}

	@Override
	@GetMapping("api/rutas")
	public Iterable<Tmio1Ruta> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	@DeleteMapping("api/rutas/{id}")
	public Tmio1Ruta delete(Tmio1Ruta ruta) throws Exception {
		// TODO Auto-generated method stub
		Optional<Tmio1Ruta> newRuta = service.findById(ruta.getId());
		service.delete(newRuta.get());
		return newRuta.get();
	}

}
