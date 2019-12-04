package co.edu.icesi.ci.talleres.delegate;

import java.util.Arrays;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;
import co.edu.icesi.ci.talleres.model.Tmio1SitiosRuta;

@Component
public class SitioRutaDelegate {
	
	RestTemplate restTemplate=new RestTemplate();
	public static String SERVER = "http://localhost:8080/api/";
	
	public List<Tmio1SitiosRuta> findAll() {
		Tmio1SitiosRuta[] buses= restTemplate.getForObject(SERVER+"sitioruta/all", Tmio1SitiosRuta[].class);
		List<Tmio1SitiosRuta> at;
		try {
			at= Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Tmio1Ruta> getAllRoutes() {
		Tmio1Ruta[] rutas= restTemplate.getForObject(SERVER+"/rutas", Tmio1Ruta[].class);
		List<Tmio1Ruta> at;
		try {
			at= Arrays.asList(rutas);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Tmio1Sitio> getAllSitio() {
		Tmio1Sitio[] sitios= restTemplate.getForObject(SERVER+"/sitio", Tmio1Sitio[].class);
		List<Tmio1Sitio> at;
		try {
			at= Arrays.asList(sitios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Tmio1SitiosRuta getByID(String id) {
		Tmio1SitiosRuta bus= restTemplate.getForObject(SERVER+"/sitioRuta"+id, Tmio1SitiosRuta.class);
		return bus;
	}

	public Tmio1Ruta findRutaByID(Integer idRuta) {
		Tmio1Ruta ruta= restTemplate.getForObject(SERVER+"rutas/"+idRuta, Tmio1Ruta.class);
		return ruta;
	}

	public Tmio1Sitio findSitioByID(Integer idSitio) {
		Tmio1Sitio bus= restTemplate.getForObject(SERVER+"sitio/"+idSitio, Tmio1Sitio.class);
		return bus;
	}
	public Tmio1SitiosRuta findById(int id) {
		Tmio1SitiosRuta bus= restTemplate.getForObject(SERVER+"sitioruta/"+id, Tmio1SitiosRuta.class);
		return bus;
	}
	public Tmio1SitiosRuta saveSitio(Tmio1SitiosRuta nuevo) {
		Tmio1SitiosRuta newBus= restTemplate.postForEntity(SERVER+"/sitioRuta", nuevo, Tmio1SitiosRuta.class).getBody();
		return newBus;	
	}
	public void removeSitio(String id) {
		restTemplate.delete(SERVER+"sitioruta/delete/"+id);	
	}
	
	public String updateSitio(Tmio1SitiosRuta nuevo) {
		HttpClient client = HttpClients.createDefault();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		try {
			Tmio1SitiosRuta x = restTemplate.patchForObject(SERVER + "/sitioruta/e", nuevo, Tmio1SitiosRuta.class);	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error";
		}
		return "Enviado";
	}

}
