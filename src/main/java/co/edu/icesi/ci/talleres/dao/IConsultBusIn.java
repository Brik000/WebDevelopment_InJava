package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;

@Repository
@Scope("singleton")
@Transactional
public class IConsultBusIn implements IConsultBus{

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findbyid(int id) {
		String jpql= "Select a from Tmio1Bus a WHERE a.id = '"+ id+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findAll() {
		String jpql= "Select a from Tmio1Bus a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Bus entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Bus entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Bus entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findbyPlaca(String placa) {
		String jpql= "Select a from Tmio1Bus a WHERE a.placa = '"+ placa+"'";
		return entityManager.createQuery(jpql).getResultList();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findbyMarca(String marca) {
		String jpql= "Select a from Tmio1Bus a WHERE a.marca = '"+ marca+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findbyModelo(BigDecimal modelo) {
		String jpql= "Select a from Tmio1Bus a WHERE a.modelo = '"+ modelo+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> sameServiceDay(String diaEspecifico) {
		List<Tmio1Bus> retorno= new ArrayList<Tmio1Bus>();
		String jpql = "SELECT pp, COUNT(pp) FROM Tmio1Servicio "
				+ "kk INNER JOIN kk.tmio1Bus pp WHERE kk.id.fechaInicio <= '" + diaEspecifico + "' "
						+ "AND kk.id.fechaFin >= '" + diaEspecifico + "'  GROUP BY pp";
		
		List<Object[]> results= entityManager.createQuery(jpql).getResultList();
		for (Object[] result: results) {
			Tmio1Bus n= (Tmio1Bus) result[0];
			int count= ((Number) result[1]).intValue();
			if( count>1) retorno.add(n);
		}
		return retorno;
	}
}
