package co.edu.icesi.ci.talleres.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

@Component
public class RutasDelegateImpl implements RutasDelegate{
	
	RestTemplate restTemplate;
	public static String SERVER = "http://localhost:8080/api/";
	
	public RutasDelegateImpl() {
		// TODO Auto-generated constructor stub
		restTemplate= new RestTemplate();

	}

	@Override
	public Tmio1Ruta saveRuta(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		Tmio1Ruta newRuta= restTemplate.postForEntity(SERVER+"rutas", ruta, Tmio1Ruta.class).getBody();
		return newRuta;
	}

	@Override
	public Tmio1Ruta validarRuta(Tmio1Ruta ruta) throws Exception {
		Tmio1Ruta newRuta= restTemplate.postForEntity(SERVER+"rutas/validar", ruta, Tmio1Ruta.class).getBody();
		return newRuta;
	}

	@Override
	public Tmio1Ruta findById(int id) throws Exception {
		// TODO Auto-generated method stub
		Tmio1Ruta ruta= restTemplate.getForObject(SERVER+"rutas/"+id, Tmio1Ruta.class);
		return ruta;
	}

	@Override
	public Iterable<Tmio1Ruta> findAll() {
		Tmio1Ruta[] rutas= restTemplate.getForObject(SERVER+"rutas", Tmio1Ruta[].class);
		List<Tmio1Ruta> at;
		try {
			at= Arrays.asList(rutas);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		restTemplate.delete(SERVER+"rutas/"+ruta.getId());

	}

}
