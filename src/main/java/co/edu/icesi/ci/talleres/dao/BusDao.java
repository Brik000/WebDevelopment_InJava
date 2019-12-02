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
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;


@Repository
@Transactional
@Scope("singleton")
public class BusDao implements IBusDao{
	
	@PersistenceContext
	private EntityManager entityManager;

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

	@Override
	public List findByPlaca(String placa) {
		
       String jpql = "Select a from Tmio1Bus a where a.placa='"+placa+"'";
		
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		
	String jpql = "Select a from Tmio1Bus a where a.marca='"+marca+"'";
		
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		
		String jpql = "Select a from Tmio1Bus a where a.modelo='"+modelo+"'";
		
		return 	entityManager.createQuery(jpql).getResultList();
		
	}

	@Override
	public List<Tmio1Bus> consultaPunto2c(String fecha) {
		List<Tmio1Bus> retorno= new ArrayList<Tmio1Bus>();
		String jpql = "SELECT pp, COUNT(pp) FROM Tmio1Servicio "
				+ "ss INNER JOIN ss.tmio1Bus pp WHERE ss.id.fechaInicio <= '" + fecha + "' "
						+ "AND ss.id.fechaFin >= '" + fecha + "'  GROUP BY pp";
		
		List<Object[]> results= entityManager.createQuery(jpql).getResultList();
		for (Object[] result: results) {
			Tmio1Bus n= (Tmio1Bus) result[0];
			int count= ((Number) result[1]).intValue();
			if( count>1) retorno.add(n);
		}
		return retorno;
	}

	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

}
