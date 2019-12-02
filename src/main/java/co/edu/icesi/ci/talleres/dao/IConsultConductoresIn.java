package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@Repository
@Scope("singleton")
@Transactional
public class IConsultConductoresIn implements IConsultConductores{
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore> findbyNombre(String nombre) {
		String jpql= "Select a from Tmio1Conductore a WHERE a.nombre = '"+ nombre+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore> findbyApellidos(String apellidos) {
		String jpql= "Select a from Tmio1Conductore a WHERE a.apellidos = '"+ apellidos+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql= "Select a from Tmio1Conductore a";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Conductore> consultaFechaEspecifica(String fechaConsulta) {
		String jpql = "SELECT pp FROM Tmio1Servicio "
				+ "kk INNER JOIN kk.tmio1Conductore pp WHERE kk.id.fechaInicio <= '" + fechaConsulta + "' "
						+ "AND kk.id.fechaFin >= '" + fechaConsulta + "'ORDER BY kk.id.fechaFin desc";
		
		return entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public Tmio1Conductore findById(String cedula) {
		String jpql= "Select a from Tmio1Conductore a WHERE a.cedula = '"+ cedula+"'";
		return (Tmio1Conductore)entityManager.createQuery(jpql).getSingleResult();
	}
}
