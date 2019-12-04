package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

@Repository
@Transactional
public class RutaDao implements IRutaDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Ruta entity) {
		entityManager.persist(entity);			
		
	}

	@Override
	public void update(Tmio1Ruta entity) {
		entityManager.merge(entity);		
		
	}
	public  Optional<Tmio1Ruta> findById(Integer codigo) {
		// TODO Auto-generated method stub
		return Optional.of(entityManager.find(Tmio1Ruta.class, codigo));
	}

	@Override
	public void delete(Tmio1Ruta entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public List<Tmio1Ruta>  findByRangoFecha(BigDecimal FechaI, BigDecimal FechaF) {
		
		String jpql = "Select a "
				+ "from Tmio1Ruta a"
				+ " where "+FechaI+"<=a.diaInicio<="+FechaF+
				"&&"+FechaI+"<=a.diaFin<="+FechaF
				;
		
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta>  findByRangoHora(BigDecimal HoraI, BigDecimal HoraF) {
		
		String jpql = "Select a "
				+ "from Tmio1Ruta a"
				+ " where "+HoraI+"<=a.horaInicio<="+HoraF+
				"&&"+HoraI+"<=a.horaFin<="+HoraF
				;
		
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> consultaPunto2b(BigDecimal fecha) {
		List<Tmio1Ruta> retorno= new ArrayList<Tmio1Ruta>();
		String jpql = "SELECT pp, COUNT(pp) FROM Tmio1Servicio "
				+ "j INNER JOIN j.tmio1Ruta pp WHERE j.id.fechaInicio <= '" + fecha + "' "
						+ "AND j.id.fechaFin >= '" + fecha + "'  GROUP BY pp";
		
		List<Object[]> results= entityManager.createQuery(jpql).getResultList();
		for (Object[] result: results) {
			Tmio1Ruta n= (Tmio1Ruta) result[0];
			int count= ((Number) result[1]).intValue();
			if( count<10) retorno.add(n);
		}
		return retorno;
	}

	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql = "Select a from Tmio1Ruta a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

}
