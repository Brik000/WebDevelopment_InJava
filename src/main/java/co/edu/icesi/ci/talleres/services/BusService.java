package co.edu.icesi.ci.talleres.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;
import co.edu.icesi.ci.talleres.repositories.BusesRepository;

@Service
public class BusService implements BusServiceIn{
	private BusesRepository busRepository;
	@Autowired
	public BusService(BusesRepository busRepository) {
		this.busRepository = busRepository;
	}
	@Override
	public void saveBus(Tmio1Bus bus) {
		busRepository.save(bus);
	}
	@Override
	public void validarBus(Tmio1Bus bus) throws Exception {
		if (bus==null) {
			throw new Exception("El bus no puede ser nulo");
		}
		if (bus.getCapacidad().compareTo(BigDecimal.ZERO) == -1) {
			throw new Exception("La capacidad debe ser mayor a 0");
		}
 		if (!(bus.getTipo().equals("T") || bus.getTipo().equals("P") || bus.getTipo().equals("A"))) {
			throw new Exception("El tipo debe ser un elemento valido");
		}
	}
	@Override
	public Optional<Tmio1Bus> findById(int id) throws Exception {
		return busRepository.findById(id); 
	}
	public void setRepository(BusesRepository busRepository) {
		this.busRepository= busRepository;
	}
	public Iterable<Tmio1Bus> findAll() {
		return busRepository.findAll();
	}
	public void delete(Tmio1Bus user) {
		busRepository.delete(user);
	}
	public BusType[] getTypes() {
		return BusType.values();
	}
	
	
}
