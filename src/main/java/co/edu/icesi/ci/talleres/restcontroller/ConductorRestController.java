package co.edu.icesi.ci.talleres.restcontroller;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface ConductorRestController {
	
	public Tmio1Conductore saveConductor(Tmio1Conductore conductor);
	public Tmio1Conductore validarConductor(Tmio1Conductore conductor) throws Exception;
	public Optional<Tmio1Conductore> findByCedula(String cedula) throws Exception;
	public Iterable<Tmio1Conductore> findAll();
	public Tmio1Conductore delete(Tmio1Conductore conductor)throws Exception;

}
