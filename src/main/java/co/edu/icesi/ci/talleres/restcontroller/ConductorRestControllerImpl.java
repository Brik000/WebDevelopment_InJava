package co.edu.icesi.ci.talleres.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.services.ConductorService;

@RestController
public class ConductorRestControllerImpl implements ConductorRestController{

	@Autowired
	private ConductorService service;
	
	@Override
	@PostMapping("api/conductores")
	public Tmio1Conductore saveConductor(@RequestBody Tmio1Conductore conductor) {
		System.out.println(conductor.getCedula());
		service.saveConductor(conductor);
		return conductor;
	}

	@Override
	@PostMapping("api/conductores/validar")
	public Tmio1Conductore validarConductor(Tmio1Conductore conductor) throws Exception {
		// TODO Auto-generated method stub
		service.validarConductor(conductor);
		return conductor;
	}

	@Override
	@GetMapping("api/conductores/{cedula}")
	public Optional<Tmio1Conductore> findByCedula(@PathVariable String cedula) throws Exception {
		// TODO Auto-generated method stub
		return service.findByCedula(cedula);
	}

	@Override
	@GetMapping("api/conductores")
	public Iterable<Tmio1Conductore> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	@DeleteMapping("api/conductores/{cedula}")
	public Tmio1Conductore delete(Tmio1Conductore conductor) throws Exception {
		// TODO Auto-generated method stub
		Optional<Tmio1Conductore> newConductor = service.findByCedula(conductor.getCedula());
		service.delete(newConductor.get());
		return newConductor.get();
	}

}
