package co.edu.icesi.ci.talleres.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.services.BusService;

@RestController
public class BusRestControllerImpl implements BusRestController{

	@Autowired
	private BusService service;
	
	@Override
	@PostMapping("api/buses")
	public Tmio1Bus saveBus(Tmio1Bus bus) {
		service.saveBus(bus);
		return bus;
	}

	@Override
	@PostMapping("api/buses/validar")
	public Tmio1Bus validarBus(Tmio1Bus bus) throws Exception {
		service.validarBus(bus);
		return bus;
	}

	@Override
	@GetMapping("api/buses/{id}")
	public Optional<Tmio1Bus> findById(@PathVariable int id) throws Exception {
		// TODO Auto-generated method stub
		return service.findById(id);
	}

	@Override
	@GetMapping("api/buses")
	public Iterable<Tmio1Bus> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	@DeleteMapping("api/buses/{id}")
	public Tmio1Bus delete(Tmio1Bus bus) throws Exception {
		Optional<Tmio1Bus> newBus = service.findById(bus.getId());
		service.delete(newBus.get());		
		return newBus.get();
	}

	@Override
	@GetMapping("api/buses/types")
	public BusType[] getTypes() {
		// TODO Auto-generated method stub
		return service.getTypes();
	}

}
