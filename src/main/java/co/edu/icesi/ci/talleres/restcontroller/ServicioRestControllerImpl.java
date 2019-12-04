package co.edu.icesi.ci.talleres.restcontroller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.services.ServiceService;

@RestController
public class ServicioRestControllerImpl implements ServiceRestController {
	
	@Autowired
	private ServiceService servicio;

	@Override
	@PostMapping("api/servicios")
	public Tmio1ServicioPK saveService(@RequestBody Tmio1ServicioPK serice) {
		// TODO Auto-generated method stub
		servicio.saveService(serice);
		return serice;
	}

	@Override
	@PostMapping("api/servicios/validar")
	public Tmio1Servicio validarService(Tmio1Servicio service) throws Exception {
		// TODO Auto-generated method stub
		servicio.validarService(service);
		return service;
	}

	@Override
	@GetMapping("api/servicios/{id}")
	public Optional<Tmio1Servicio> findById(Tmio1ServicioPK id) {
		// TODO Auto-generated method stub
		return servicio.findById(id);

	}

	@Override
	public Tmio1Servicio saveService2(Tmio1Servicio service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Tmio1Bus> findAllBuses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Tmio1Conductore> findAllDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Tmio1Ruta> findAllRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Tmio1Servicio> findAllServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tmio1Bus> findByBusId(Integer busId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tmio1Conductore> findByDriverId(String driverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tmio1Ruta> findByRouteId(Integer routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tmio1ServicioPK> findPKId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Tmio1Servicio> filtrar(LocalDate fechaInicio) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@PatchMapping("/api/service")
	public Tmio1Servicio updateServicio(@RequestBody Tmio1Servicio nuevoServ) {
		servicio.saveService2(nuevoServ);
		return nuevoServ;
	}
	

	@DeleteMapping("/api/service/{id}")
	public void deleteServicio (@PathVariable("id") String hash) {
		Tmio1Servicio ser = servicio.findByHash(hash);
		servicio.delete(ser);
	}

	@GetMapping("api/service/{id}")
	public Tmio1Servicio findByHash(@PathVariable("id") String hash) {
		try {
			return servicio.findByHash(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
