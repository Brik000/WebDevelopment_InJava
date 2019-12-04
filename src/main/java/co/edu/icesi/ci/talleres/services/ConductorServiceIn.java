package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;

public interface ConductorServiceIn {
	
	public void saveConductor(Tmio1Conductore conductor);
	public void validarConductor(Tmio1Conductore conductor) throws Exception;
	public Optional<Tmio1Conductore> findByCedula(String cedula) throws Exception;
	public void setRepository(ConductoresRepository conductoRepository);
	public Iterable<Tmio1Conductore> findAll();
	public void delete(Tmio1Conductore user);
	
}
