package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

public interface IConsultBus {
	public List<Tmio1Bus> findbyid(int id);
	public List<Tmio1Bus> findbyPlaca(String placa);
	public List<Tmio1Bus> findbyMarca(String marca);
	public List<Tmio1Bus> findbyModelo(BigDecimal modelo);
	public List<Tmio1Bus> sameServiceDay(String diaEspecifico);
	
	public List<Tmio1Bus> findAll();
	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
}
