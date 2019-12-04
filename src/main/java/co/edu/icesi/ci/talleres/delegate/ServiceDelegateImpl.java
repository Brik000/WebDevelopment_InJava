package co.edu.icesi.ci.talleres.delegate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

@Component
public class ServiceDelegateImpl implements ServiceDelegate{
	
	RestTemplate restTemplate;
	public static String SERVER = "http://localhost:8080/api/";
	
	public ServiceDelegateImpl() {
		restTemplate= new RestTemplate();
	}

	@Override
	public Tmio1ServicioPK saveService(Tmio1ServicioPK serice) {
		// TODO Auto-generated method stub
		Tmio1ServicioPK newServicio= restTemplate.postForEntity(SERVER+"servicios", serice, Tmio1ServicioPK.class).getBody();
		return newServicio;
	}

	@Override
	public Tmio1Servicio validarService(Tmio1Servicio service) throws Exception {
		Tmio1Servicio newServicio= restTemplate.postForEntity(SERVER+"servicios/validar", service, Tmio1Servicio.class).getBody();
		return newServicio;
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK id) {
		Tmio1Servicio servicio= restTemplate.getForObject(SERVER+"servicios/"+id, Tmio1Servicio.class);
		return servicio;
	}

	@Override
	public Tmio1Servicio saveService2(Tmio1Servicio service) {
		Tmio1Servicio newServicio2= restTemplate.postForEntity(SERVER+"servicios2", service, Tmio1Servicio.class).getBody();
		return newServicio2;
	}
	
	@Override
	public String editService(Tmio1Servicio nuevo) {
		
		Tmio1Servicio service= restTemplate.patchForObject(SERVER+"servicios", nuevo, Tmio1Servicio.class);
		return "Guardado";
	}
	
	public void delete(String hash) {
		restTemplate.delete(SERVER + "service/" + hash);
	}

	@Override
	public Iterable<Tmio1Bus> findAllBuses() {
		Tmio1Bus[] buses= restTemplate.getForObject(SERVER+"servicios/buses", Tmio1Bus[].class);
		List<Tmio1Bus> at;
		try {
			at= Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Conductore> findAllDrivers() {
		Tmio1Conductore[] conductores= restTemplate.getForObject(SERVER+"servicios/conductores", Tmio1Conductore[].class);
		List<Tmio1Conductore> at;
		try {
			at= Arrays.asList(conductores);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Ruta> findAllRoutes() {
		Tmio1Ruta[] rutas= restTemplate.getForObject(SERVER+"servicios/rutas", Tmio1Ruta[].class);
		List<Tmio1Ruta> at;
		try {
			at= Arrays.asList(rutas);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Servicio> findAllServices() {
		Tmio1Servicio[] servicios= restTemplate.getForObject(SERVER+"servicios", Tmio1Servicio[].class);
		List<Tmio1Servicio> at;
		try {
			at= Arrays.asList(servicios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tmio1Bus findByBusId(Integer busId) {
		Tmio1Bus bus= restTemplate.getForObject(SERVER+"servicios/buses/"+busId, Tmio1Bus.class);
		return bus;
	}

	@Override
	public Tmio1Conductore findByDriverId(String driverId) {
		Tmio1Conductore conductor= restTemplate.getForObject(SERVER+"servicios/conductores/"+driverId, Tmio1Conductore.class);
		return conductor;
	}

	@Override
	public Tmio1Ruta findByRouteId(Integer routeId) {
		Tmio1Ruta ruta= restTemplate.getForObject(SERVER+"servicios/rutas/"+routeId, Tmio1Ruta.class);
		return ruta;
	}

	@Override
	public Optional<Tmio1ServicioPK> findPKId(String id) {
		Tmio1ServicioPK servicePK= restTemplate.getForObject(SERVER+"servicios/pk/"+id, Tmio1ServicioPK.class);
		return Optional.of(servicePK);
	}

	@Override
	public Iterable<Tmio1Servicio> filtrar(LocalDate fechaInicio) {
		Tmio1Servicio[] servicios= restTemplate.postForEntity(SERVER+"servicios/filtrar", fechaInicio, Tmio1Servicio[].class).getBody();
		List<Tmio1Servicio> at;
		try {
			at= Arrays.asList(servicios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Tmio1Servicio findByHash(String tempHash) {
			Tmio1Servicio nuevoServicio= restTemplate.getForObject(SERVER+"service/", Tmio1Servicio.class);
		return nuevoServicio;
	}

}
