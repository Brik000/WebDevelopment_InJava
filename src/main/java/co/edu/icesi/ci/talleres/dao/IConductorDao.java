package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;



public interface IConductorDao {
	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	
	public List<Tmio1Conductore>  findByNombre(String nombre);
	
	public List<Tmio1Conductore>  findByApellido(String apellido);

	public List<Tmio1Conductore> consultaPunto2a(BigDecimal fecha);

	
	
	public List<Tmio1Conductore> findAll();
	
	List<Tmio1Conductore> consultaF(String fechaConsulta);
	

}
