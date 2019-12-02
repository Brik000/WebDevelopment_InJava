package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface RutaServiceIn {
	public void saveRuta(Tmio1Ruta ruta);
	public void validarRuta(Tmio1Ruta ruta) throws Exception;
	public Optional<Tmio1Ruta> findById(int id) throws Exception;
}
