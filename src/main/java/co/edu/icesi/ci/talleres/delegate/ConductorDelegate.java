package co.edu.icesi.ci.talleres.delegate;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface ConductorDelegate {
	
	public Tmio1Conductore saveConductor(Tmio1Conductore conductor);
	public Tmio1Conductore validarConductor(Tmio1Conductore conductor) throws Exception;
	public Tmio1Conductore findByCedula(String cedula) throws Exception;
	public Iterable<Tmio1Conductore> findAll();
	public void delete(Tmio1Conductore conductor);

}
