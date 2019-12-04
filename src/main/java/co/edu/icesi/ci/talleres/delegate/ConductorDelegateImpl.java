package co.edu.icesi.ci.talleres.delegate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;

@Component
public class ConductorDelegateImpl implements ConductorDelegate {
	
	RestTemplate restTemplate;
	public static String SERVER = "http://localhost:8080/api/";
	
	public ConductorDelegateImpl(){
		restTemplate= new RestTemplate();
	}

	@Override
	public Tmio1Conductore saveConductor(Tmio1Conductore conductor) {
		System.out.println(conductor.getCedula());
		Tmio1Conductore newConductor= restTemplate.postForEntity(SERVER+"conductores", conductor, Tmio1Conductore.class).getBody();
		System.out.println(newConductor.getCedula());
		return newConductor;
	}

	@Override
	public Tmio1Conductore validarConductor(Tmio1Conductore conductor) throws Exception {
		Tmio1Conductore newConductor= restTemplate.postForEntity(SERVER+"conductores/validar", conductor, Tmio1Conductore.class).getBody();
		return newConductor;
	}

	@Override
	public Tmio1Conductore findByCedula(String cedula) throws Exception {
		Tmio1Conductore conductor= restTemplate.getForObject(SERVER+"conductores/"+cedula, Tmio1Conductore.class);
		return conductor;
	}


	@Override
	public Iterable<Tmio1Conductore> findAll() {
		Tmio1Conductore[] conductores= restTemplate.getForObject(SERVER+"conductores", Tmio1Conductore[].class);
		List<Tmio1Conductore> at;
		try {
			at= Arrays.asList(conductores);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Tmio1Conductore conductor) {
		restTemplate.delete(SERVER+"conductores/"+conductor.getCedula());
	}

}
