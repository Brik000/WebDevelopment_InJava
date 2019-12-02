package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;


public interface ISitioRutaDao {
	
	
	public List<Tmio1SitiosRuta> findAll();
	public void save(Tmio1SitiosRuta entity);
	public void update(Tmio1SitiosRuta entity);
	public void delete(Tmio1SitiosRuta entity);


}
