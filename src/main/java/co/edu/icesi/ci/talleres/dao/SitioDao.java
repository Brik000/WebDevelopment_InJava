package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1Sitio;


@Repository
@Transactional
@Scope("singleton")
public class SitioDao implements ISitioDao{
	@PersistenceContext
	private EntityManager entityManager;

	//TODO puede fallar por el a.descripcion
		@SuppressWarnings("unchecked")
		@Override
		public List<Tmio1Sitio> findbyDescripcion(String descripcion) {
			 String jpql = "Select a from Tmio1Sitio a where a.descripcion='"+descripcion+"'";
				
				return 	entityManager.createQuery(jpql).getResultList();
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Tmio1Sitio> findbyNombre(String nombre) {
			 String jpql = "Select a from Tmio1Sitio a where a.nombre='"+nombre+"'";
				
				return 	entityManager.createQuery(jpql).getResultList();

		}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Sitio> findbyId(long id) {
		 String jpql = "Select a from Tmio1Sitio a where a.id='"+id+"'";
			
			return 	entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Sitio> findAll() {
		String jpql= "Select a from Tmio1Sitio a ";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Sitio entity) {
		entityManager.persist(entity);		
	}

	@Override
	public void update(Tmio1Sitio entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Tmio1Sitio entity) {
		entityManager.remove(entity);
		
	}

}
