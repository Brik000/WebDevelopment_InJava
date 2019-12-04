package co.edu.icesi.ci.talleres.restcontroller;

import java.util.Optional;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface RutaRestController {
	
	public Tmio1Ruta saveRuta(Tmio1Ruta ruta);
	public Tmio1Ruta validarRuta(Tmio1Ruta ruta) throws Exception;
	public Optional<Tmio1Ruta> findById(int id) throws Exception;
	public Iterable<Tmio1Ruta> findAll();
	public Tmio1Ruta delete(Tmio1Ruta ruta) throws Exception;

}
