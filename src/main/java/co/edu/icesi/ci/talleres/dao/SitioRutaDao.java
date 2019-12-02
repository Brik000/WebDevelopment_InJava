package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;

@Repository
@Transactional
public class SitioRutaDao implements ISitioRutaDao{

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1SitiosRuta> findAll() {
		String jpql= "Select a from Tmio1SitiosRuta a ";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1SitiosRuta entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(Tmio1SitiosRuta entity) {
		entityManager.merge(entity);		
	}

	@Override
	public void delete(Tmio1SitiosRuta entity) {
		entityManager.remove(entity);
		
	}

}
