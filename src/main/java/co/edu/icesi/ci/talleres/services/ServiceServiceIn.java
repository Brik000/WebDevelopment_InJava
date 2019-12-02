package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

public interface ServiceServiceIn {

	public void saveService(Tmio1ServicioPK serice);
	public void validarService(Tmio1Servicio service) throws Exception;
	public Optional<Tmio1Servicio> findById(Tmio1ServicioPK id);
}
