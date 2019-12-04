package co.edu.icesi.ci.talleres.services;

import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

public interface ISitioService {
	public Iterable<Tmio1Sitio> findAll();
	public Tmio1Sitio saveSitio(Tmio1Sitio sitio);
	public Tmio1Sitio updateSitio(Tmio1Sitio sitio);
	public Tmio1Sitio removeSitio(Tmio1Sitio sitio);
	public Tmio1Sitio findById(long id);

}
