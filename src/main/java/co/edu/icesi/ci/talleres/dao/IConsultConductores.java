package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

public interface IConsultConductores {
	public List<Tmio1Conductore> findbyNombre(String nombre);
	public List<Tmio1Conductore> findbyApellidos(String apellidos);
	public List<Tmio1Conductore> findAll();
	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public List<Tmio1Conductore> consultaFechaEspecifica(String fechaConsulta);
	public Tmio1Conductore findById(String cedula);
	
}
