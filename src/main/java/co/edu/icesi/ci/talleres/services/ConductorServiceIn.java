package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface ConductorServiceIn {
	public void saveConductor(Tmio1Conductore conductor);
	public void validarConductor(Tmio1Conductore conductor) throws Exception;
	public Optional<Tmio1Conductore> findByCedula(String cedula) throws Exception;
}
