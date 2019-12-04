package co.edu.icesi.ci.talleres.delegate;

import java.time.LocalDate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

public interface ServiceDelegate {

	public Tmio1ServicioPK saveService(Tmio1ServicioPK serice);
	public Tmio1Servicio validarService(Tmio1Servicio service) throws Exception;
	public Tmio1Servicio findById(Tmio1ServicioPK id);
	public Tmio1Servicio saveService2(Tmio1Servicio service);
	public Iterable<Tmio1Bus> findAllBuses();
	public Iterable<Tmio1Conductore> findAllDrivers();
	public Iterable<Tmio1Ruta> findAllRoutes();
	public Iterable<Tmio1Servicio> findAllServices();
	public Tmio1Bus findByBusId(Integer busId);
	public Tmio1Conductore findByDriverId(String driverId);
	public Tmio1Ruta findByRouteId(Integer routeId);
	public Tmio1ServicioPK findPKId(String id);
	public Iterable<Tmio1Servicio> filtrar(LocalDate fechaInicio);
	public String editService(Tmio1Servicio nuevo);
	public void delete(String hash);
	
}
