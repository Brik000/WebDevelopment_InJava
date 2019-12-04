package co.edu.icesi.ci.talleres.delegate;

import java.util.Arrays;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

@Component
public class SitioDelegate {
	RestTemplate restTemplate=new RestTemplate();
	public static String SERVER = "http://localhost:8080/api/";
	
	public List<Tmio1Sitio> findAll() {
		Tmio1Sitio[] buses= restTemplate.getForObject(SERVER+"sitio", Tmio1Sitio[].class);
		List<Tmio1Sitio> at;
		try {
			at= Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Tmio1Sitio findById(int id) {
		Tmio1Sitio bus= restTemplate.getForObject(SERVER+"sitio/"+id, Tmio1Sitio.class);
		return bus;
	}
	public Tmio1Sitio saveSitio(Tmio1Sitio nuevo) {
		Tmio1Sitio newBus= restTemplate.postForEntity(SERVER+"sitio", nuevo, Tmio1Sitio.class).getBody();
		return newBus;	
	}
	public void removeSitio(String id) {
		restTemplate.delete(SERVER+"sitio/delete/"+id);	
	}
	
	public String updateSitio(Tmio1Sitio nuevo) {
		HttpClient client = HttpClients.createDefault();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		try {
			Tmio1Sitio x = restTemplate.patchForObject(SERVER + "/sitio/e", nuevo, Tmio1Sitio.class);	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error";
		}
		return "Enviado";
	}

}
