package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;


public interface IBusDao {
	
	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	
	public  List<Tmio1Bus> findByPlaca(String placa);
	
	public List<Tmio1Bus> findByMarca(String marca);
	
	public List<Tmio1Bus> findByModelo(BigDecimal modelo);
	
	public List<Tmio1Bus> consultaPunto2c(String fecha);

	
	
	public List<Tmio1Bus> findAll();
	

}
