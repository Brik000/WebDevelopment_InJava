package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface IConsultRutas {
	public List<Tmio1Ruta> findbyHoras(BigDecimal horaInicial,BigDecimal horaFinal);
	public List<Tmio1Ruta> findbyFechas(BigDecimal fechaInicial,BigDecimal fechaFinal);
	
	public List<Tmio1Ruta> findbyFechaEspecifica(String fechaFinal);
	public List<Tmio1Ruta> findbyId(String id);
	
	public List<Tmio1Ruta> findAll();
	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	
	
	
}
