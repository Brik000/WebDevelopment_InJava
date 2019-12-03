package co.edu.icesi.ci.talleres.delegate;


import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface BusesDelegate {
	
	public Tmio1Bus saveBus(Tmio1Bus bus);
	public Tmio1Bus validarBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus findById(int id) throws Exception;
	public Iterable<Tmio1Bus> findAll();
	public void delete(Tmio1Bus bus);
	public BusType[] getTypes();

}
