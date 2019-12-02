package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Servicio;

@Repository
@Scope("singleton")
@Transactional
public class IConsultServicesIn implements IConsultServices{
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql= "Select a from Tmio1Servicio a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Servicio entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Servicio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Servicio entity) {
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Servicio findbyID(String cedula, int idBus, int idRuta, String fechaInicio, String fechaFin) {
		String jpql= "Select a from Tmio1Servicio a WHERE a.id.idBus = '"+ idBus+"' AND a.id.idRuta = '"+ idRuta+"' AND a.id.cedulaConductor= '"+ cedula+"' AND a.id.fechaInicio='"+fechaInicio+"' AND a.id.fechaFin='"+fechaFin+"'";
		return (Tmio1Servicio)entityManager.createQuery(jpql).getSingleResult();
	}
	
}
