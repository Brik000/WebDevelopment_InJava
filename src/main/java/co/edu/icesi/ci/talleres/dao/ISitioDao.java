package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Sitio;


public interface ISitioDao {
	
public List<Tmio1Sitio> findbyId(long id);

public List<Tmio1Sitio> findbyDescripcion(String descripcion);
public List<Tmio1Sitio> findbyNombre(String nombre);
	
	public List<Tmio1Sitio> findAll();
	public void save(Tmio1Sitio entity);
	public void update(Tmio1Sitio entity);
	public void delete(Tmio1Sitio entity);

}
