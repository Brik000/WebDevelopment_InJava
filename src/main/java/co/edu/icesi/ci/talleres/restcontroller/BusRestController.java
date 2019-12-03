package co.edu.icesi.ci.talleres.restcontroller;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface BusRestController {
	
	public Tmio1Bus saveBus(Tmio1Bus bus);
	public Tmio1Bus validarBus(Tmio1Bus bus)throws Exception;
	public Optional<Tmio1Bus> findById(int id)throws Exception;	
	public Iterable<Tmio1Bus> findAll();
	public Tmio1Bus delete(Tmio1Bus bus)throws Exception;
	public BusType[] getTypes();

}
