package co.edu.icesi.ci.talleres.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.repositories.BusesRepository;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;
import co.edu.icesi.ci.talleres.repositories.ServiciosRepository;

@Service
public class ServiceService implements ServiceServiceIn{
	
	@Autowired
	private ServiciosRepository serviceRepository;
	
	@Autowired
	private ConductoresRepository driverRepos;
	@Autowired
	private RutasRepository routeRepos;
	@Autowired
	private BusesRepository busRepos;
	
	
	@Autowired
	public ServiceService(ServiciosRepository serviciosRepository) {
		this.serviceRepository= serviciosRepository;
	}

	@Override
	public void saveService(Tmio1ServicioPK service) {
		try {
			Tmio1Servicio nuevo= new Tmio1Servicio();
			nuevo.setTmio1Bus(findByBusId(service.getIdBus()).get());
			nuevo.setTmio1Conductore(findByDriverId(service.getCedulaConductor()).get());
			nuevo.setTmio1Ruta(findByRouteId(service.getIdRuta()).get());
			nuevo.setId(service);
			serviceRepository.save(nuevo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveService2(Tmio1Servicio service) {
		try {
			serviceRepository.save(service);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void validarService(Tmio1Servicio service) throws Exception {
	}
	public void setRepository(ServiciosRepository serviceRepository) {
		this.serviceRepository= serviceRepository;
	}
	@Override
	public Optional<Tmio1Servicio> findById(Tmio1ServicioPK id) {
		return serviceRepository.findById(id);
	}
	
	@Override
	public Iterable<Tmio1Bus> findAllBuses() {
		return busRepos.findAll();
	}

	@Override
	public Iterable<Tmio1Conductore> findAllDrivers() {
		return driverRepos.findAll();
	}

	@Override
	public Iterable<Tmio1Ruta> findAllRoutes() {
		return routeRepos.findAll();
	}
	@Override
	public Iterable<Tmio1Servicio> findAllServices() {
		return serviceRepository.findAll();
	}

	@Override
	public Optional<Tmio1Bus> findByBusId(Integer busId) {
		return busRepos.findById(busId);
	}

	@Override
	public Optional<Tmio1Conductore> findByDriverId(String driverId) {
		return driverRepos.findById(driverId);
	}

	@Override
	public Optional<Tmio1Ruta> findByRouteId(Integer routeId) {
		return routeRepos.findById(routeId);
	}
	
	@Override
	public Optional<Tmio1ServicioPK> findPKId(String id) {
		Iterable<Tmio1Servicio> iterable= serviceRepository.findAll();
		Iterator<Tmio1Servicio> i= iterable.iterator();
		while (i.hasNext()) {
			Tmio1Servicio search= i.next();
			if(search.getId().toString().equals(id)) {
				return Optional.ofNullable(search.getId());
			}
		}
		return null;
	}
	@Override
	public Iterable<Tmio1Servicio> filtrar(LocalDate fechaInicio) {
		Iterable<Tmio1Servicio> filt= this.findAllServices();
	    int contador = 0;
	    for (Object i : filt) {
	    	contador++;
	    }
		Tmio1Servicio lista[]= new Tmio1Servicio[contador];
		filt= this.findAllServices();
		contador= 0;
	    for (Tmio1Servicio i : filt) {
	    	if(i.getId().getFechaInicio().compareTo(fechaInicio)==0) {
	    		lista[contador]= i;
	    		contador++;
	    	}
	    }
	    if(contador==0) {
	    	return null;
	    }
	    
	    
	    
		Iterable<Tmio1Servicio> nuevo= Arrays.asList(lista);
		return nuevo;
	}
}
