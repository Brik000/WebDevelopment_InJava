package co.edu.icesi.ci.talleres.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.SitioDao;
import co.edu.icesi.ci.talleres.dao.SitioRutaDao;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;

@Service
public class SitiorutaService {
	@Autowired
	private SitioRutaDao sitioDao;

	public List<Tmio1SitiosRuta> findAll() {
		// TODO Auto-generated method stub
		return sitioDao.findAll();
	}

	public Tmio1SitiosRuta saveSitio(Tmio1SitiosRuta sitio) {
		try {
			sitioDao.save(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	public Tmio1SitiosRuta updateSitio(Tmio1SitiosRuta sitio) {
		try {
			sitioDao.update(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	public Tmio1SitiosRuta removeSitio(Tmio1SitiosRuta sitio) {
		try {
			sitioDao.delete(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	public Tmio1SitiosRuta findById(String id) {
		Tmio1SitiosRuta respuesta = null;
		
		Iterable<Tmio1SitiosRuta> todos = findAll();

		for (Tmio1SitiosRuta s : todos) {	
			if(s.getHash()==Integer.parseInt(id)){
				respuesta = s;
				break;
			}
		}
		
		
		return respuesta;
	}


}
