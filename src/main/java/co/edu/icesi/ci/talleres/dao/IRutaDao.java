package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;


public interface IRutaDao {
	
	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	
	public List<Tmio1Ruta>  findByRangoFecha(BigDecimal FechaI,BigDecimal FechaF);
	
	public List<Tmio1Ruta>  findByRangoHora(BigDecimal HoraI, BigDecimal HoraF);
	
	
	public List<Tmio1Ruta>  consultaPunto2b(BigDecimal fecha);


	public List<Tmio1Ruta> findAll();
}


