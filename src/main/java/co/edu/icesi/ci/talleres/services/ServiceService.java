package co.edu.icesi.ci.talleres.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.IConsultConductoresIn;
import co.edu.icesi.ci.talleres.dao.IConsultServicesIn;
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
	private IConsultServicesIn serviceRepository;
	
	@Autowired
	private IConsultConductoresIn driverRepos;
	@Autowired
	private RutasRepository routeRepos;
	@Autowired
	private BusesRepository busRepos;
	
	
	@Autowired
	public ServiceService(IConsultServicesIn serviciosRepository) {
		this.serviceRepository= serviciosRepository;
	}

	@Transactional
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
	public void setRepository(IConsultServicesIn serviceRepository) {
		this.serviceRepository= serviceRepository;
	}
	@Override
	public Optional<Tmio1Servicio> findById(Tmio1ServicioPK id) {
		return Optional.of(serviceRepository.findbyID(id.getCedulaConductor(), id.getIdBus(), id.getIdRuta(), id.getFechaInicio().toString(), id.getFechaFin().toString()));
	}
	public Iterable<Tmio1Bus> findAllBuses() {
		return busRepos.findAll();
	}

	public Iterable<Tmio1Conductore> findAllDrivers() {
		return driverRepos.findAll();
	}

	public Iterable<Tmio1Ruta> findAllRoutes() {
		return routeRepos.findAll();
	}
	public Iterable<Tmio1Servicio> findAllServices() {
		return serviceRepository.findAll();
	}

	public Optional<Tmio1Bus> findByBusId(Integer busId) {
		return busRepos.findById(busId);
	}

	public Optional<Tmio1Conductore> findByDriverId(String driverId) {
		return Optional.of(driverRepos.findById(driverId));
	}

	public Optional<Tmio1Ruta> findByRouteId(Integer routeId) {
		return routeRepos.findById(routeId);
	}
	
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

	public Iterable<Tmio1Servicio> filtrar(LocalDate fechaInicio) {
		Iterable<Tmio1Servicio> filt= this.findAllServices();
	    int counter = 0;
	    for (Object i : filt) {
	        counter++;
	    }
		Tmio1Servicio lista[]= new Tmio1Servicio[counter];
		filt= this.findAllServices();
		counter= 0;
	    for (Tmio1Servicio i : filt) {
	    	if(i.getId().getFechaInicio().compareTo(fechaInicio)==0) {
	    		lista[counter]= i;
	    		counter++;
	    	}
	    }
	    if(counter==0) {
	    	return null;
	    }
		Iterable<Tmio1Servicio> nuevo= Arrays.asList(lista);
		return nuevo;
	}
}
