package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface BusServiceIn {
	
	public void saveBus(Tmio1Bus bus);
	public void validarBus(Tmio1Bus bus) throws Exception;
	public Optional<Tmio1Bus> findById(int id) throws Exception;	
	public Iterable<Tmio1Bus> findAll();
	public void delete(Tmio1Bus user);
	public BusType[] getTypes();
}
