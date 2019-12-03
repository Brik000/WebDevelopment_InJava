package co.edu.icesi.ci.talleres.delegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;


@Component
public class BusesDelegateImpl implements BusesDelegate{

	RestTemplate restTemplate;
	public static String SERVER = "http://localhost:8080/api/";
	
	public BusesDelegateImpl() {
		restTemplate= new RestTemplate();
	}
	
	@Override
	public Tmio1Bus saveBus(Tmio1Bus bus) {
		Tmio1Bus newBus= restTemplate.postForEntity(SERVER+"buses", bus, Tmio1Bus.class).getBody();
		return newBus;
	}

	@Override
	public Tmio1Bus validarBus(Tmio1Bus bus) throws Exception {
		Tmio1Bus newBus= restTemplate.postForEntity(SERVER+"buses/validar", bus, Tmio1Bus.class).getBody();
		return newBus;
	}

	@Override
	public Tmio1Bus findById(int id) throws Exception {
		Tmio1Bus bus= restTemplate.getForObject(SERVER+"buses/"+id, Tmio1Bus.class);
		return bus;
	}

	@Override
	public Iterable<Tmio1Bus> findAll() {
		Tmio1Bus[] buses= restTemplate.getForObject(SERVER+"buses", Tmio1Bus[].class);
		List<Tmio1Bus> at;
		try {
			at= Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Tmio1Bus bus) {
		restTemplate.delete(SERVER+"buses/"+bus.getId());
		
	}

	@Override
	public BusType[] getTypes() {
		Tmio1Bus[] buses= restTemplate.getForObject(SERVER+"buses/types", Tmio1Bus[].class);
		List<Tmio1Bus> at;
		List<BusType> tp= new ArrayList<BusType>();
		BusType[] types= new BusType[buses.length];
		try {
			at= Arrays.asList(buses);
			for(Tmio1Bus bus: at) {
				tp.add(bus.getTipo());
			}
			types= (BusType[]) tp.toArray();
			return types;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
