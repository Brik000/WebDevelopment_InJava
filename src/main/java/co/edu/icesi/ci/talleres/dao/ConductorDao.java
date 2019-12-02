package co.edu.icesi.ci.talleres.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@Repository
@Transactional
@Scope("singleton")
public class ConductorDao implements IConductorDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore>  findByNombre(String nombre){
		String jpql= "Select a from Tmio1Conductore a WHERE a.nombre = '"+ nombre+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore>  findByApellido(String apellido) {
		String jpql= "Select a from Tmio1Conductore a WHERE a.apellidos = '"+ apellido+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql= "Select a from Tmio1Conductore a ";
		return entityManager.createQuery(jpql).getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore> consultaF(String fechaConsulta) {
		String jpql = "SELECT pp FROM Tmio1Servicio "
				+ "d INNER JOIN dd.tmio1Conductore pp WHERE dd.id.fechaInicio <= '" + fechaConsulta + "' "
						+ "AND dd.id.fechaFin >= '" + fechaConsulta + "'ORDER BY dd.id.fechaFin desc";
		
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Conductore entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Conductore entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Conductore entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Tmio1Conductore> consultaPunto2a(BigDecimal fechaConsulta) {
		return null;
	}
	
//	@Override
//	public Tmio1Conductore findById(String cedula) {
//		String jpql= "Select a from Tmio1Conductore a WHERE a.cedula = '"+ cedula+"'";
//		return (Tmio1Conductore)entityManager.createQuery(jpql).getSingleResult();
//	}
	
}
