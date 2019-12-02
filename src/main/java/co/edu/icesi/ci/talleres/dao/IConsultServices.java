package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;

public interface IConsultServices {
	
	public List<Tmio1Servicio> findAll();
	public void save(Tmio1Servicio entity);
	public void update(Tmio1Servicio entity);
	public void delete(Tmio1Servicio entity);
	public Tmio1Servicio findbyID(String cedula, int idBus,int idRuta, String fechaInicio, String fechacFin);
}
