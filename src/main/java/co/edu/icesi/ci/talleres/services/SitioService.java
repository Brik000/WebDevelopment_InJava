package co.edu.icesi.ci.talleres.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.SitioDao;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
@Service
public class SitioService implements ISitioService{
	
	@Autowired
	private SitioDao sitioDao;

	@Override
	public Iterable<Tmio1Sitio> findAll() {
		// TODO Auto-generated method stub
		return sitioDao.findAll();
	}

	@Override
	public Tmio1Sitio saveSitio(Tmio1Sitio sitio) {
		try {
			sitioDao.save(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1Sitio updateSitio(Tmio1Sitio sitio) {
		try {
			sitioDao.update(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1Sitio removeSitio(Tmio1Sitio sitio) {
		try {
			sitioDao.delete(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1Sitio findById(long id) {
		try {
			return sitioDao.findbyId(id).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
