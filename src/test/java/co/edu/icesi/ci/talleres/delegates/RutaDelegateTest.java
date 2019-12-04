package co.edu.icesi.ci.talleres.delegates;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;

import co.edu.icesi.ci.talleres.delegate.BusDelegateImp;
import co.edu.icesi.ci.talleres.delegate.RutaDelegateImp;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RutaDelegateTest {
	@Mock
	private RestTemplate restTemplate;
	

	@InjectMocks
	@Autowired
	private RutaDelegateImp busDelegate;
	
	final String URI_SERVER = "http://localhost:8080/api/";
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void MostrarBusTest() {
		Tmio1Ruta bus1 = new Tmio1Ruta();
		Tmio1Ruta bus2 = new Tmio1Ruta();
		
		bus1.setId(123);
		bus1.setNumero("1221");
		bus1.setDiaInicio(new BigDecimal("123"));
		bus1.setDiaFin(new BigDecimal("223"));
		bus1.setHoraInicio(new BigDecimal("20"));
		bus1.setHoraFin(new BigDecimal("30"));
		
		bus2.setId(323);
		bus2.setNumero("4621");
		bus2.setDiaInicio(new BigDecimal("223"));
		bus2.setDiaFin(new BigDecimal("423"));
		bus2.setHoraInicio(new BigDecimal("21"));
		bus2.setHoraFin(new BigDecimal("31"));
		
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Ruta[] buses = {bus1, bus2};
		
	Mockito
    .when(restTemplate.getForObject(URI_SERVER + "rutas", Tmio1Ruta[].class))
    .thenReturn(buses);

	Iterable<Tmio1Ruta> employee = busDelegate.getRutas();
    
	assertEquals(bus1.getId(), employee.iterator().next().getId());

	}
	
	@Test
	public void AgregarBusTest() {
		
		Tmio1Ruta bus1 = new Tmio1Ruta();
		
		bus1.setId(123);
		bus1.setNumero("1221");
		bus1.setDiaInicio(new BigDecimal("123"));
		bus1.setDiaFin(new BigDecimal("223"));
		bus1.setHoraInicio(new BigDecimal("20"));
		bus1.setHoraFin(new BigDecimal("30"));
		
		Mockito.when(restTemplate.postForEntity(URI_SERVER + "rutas", bus1, Tmio1Ruta.class)).thenReturn(new ResponseEntity<Tmio1Ruta>(bus1, HttpStatus.OK));
	
		Mockito.when(restTemplate.getForObject(URI_SERVER + "rutas/"+bus1.getId(), Tmio1Ruta.class)).thenReturn(bus1);

	
	Tmio1Ruta employee = busDelegate.getRuta(bus1.getId());
    
	assertEquals(bus1.getId(), employee.getId());

	}
	
	
}
