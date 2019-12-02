package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

@Repository
@Scope("singleton")
@Transactional
public class IConsultRutasIn implements IConsultRutas{

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Ruta> findbyHoras(BigDecimal horaInicial, BigDecimal horaFinal) {
		String jpql= "Select a from Tmio1Ruta a WHERE a.horaInicio > '"+ horaInicial+"' AND a.horaFin <= '"+ horaFinal+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
	public List<Tmio1Ruta> findbyId(int id) {
		String jpql= "Select a from Tmio1Ruta a WHERE a.id = '"+ id+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Ruta> findbyFechas(BigDecimal fechaInicial, BigDecimal fechaFinal) {
		String jpql= "Select a from Tmio1Ruta a WHERE a.diaInicio > '"+ fechaInicial+"' AND a.diaFin <= '"+ fechaFinal+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql= "Select a from Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Ruta entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Ruta entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Ruta entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Tmio1Ruta> findbyFechaEspecifica(String fechaFinal) {
		List<Tmio1Ruta> retorno= new ArrayList<Tmio1Ruta>();
		String jpql = "SELECT pp, COUNT(pp) FROM Tmio1Servicio "
				+ "kk INNER JOIN kk.tmio1Ruta pp WHERE kk.id.fechaInicio <= '" + fechaFinal + "' "
						+ "AND kk.id.fechaFin >= '" + fechaFinal + "'  GROUP BY pp";
		
		List<Object[]> results= entityManager.createQuery(jpql).getResultList();
		for (Object[] result: results) {
			Tmio1Ruta n= (Tmio1Ruta) result[0];
			int count= ((Number) result[1]).intValue();
			if( count<10) retorno.add(n);
		}
		return retorno;
	}

	@Override
	public List<Tmio1Ruta> findbyId(String id) {
		String jpql= "Select a from Tmio1Ruta a WHERE a.id = '"+ id+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
}
