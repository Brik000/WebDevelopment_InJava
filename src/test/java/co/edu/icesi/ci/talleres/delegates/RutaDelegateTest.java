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


import co.edu.icesi.ci.talleres.delegate.RutasDelegateImpl;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RutaDelegateTest {
	@Mock
	private RestTemplate restTemplate;
	

	@InjectMocks
	@Autowired
	private RutasDelegateImpl rutaDelegate;
	
	final String URI_SERVER = "http://localhost:8080/api/";
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void MostrarBusTest() {
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		
		ruta1.setId(1);
		ruta1.setNumero("1221");
		ruta1.setDiaInicio(new BigDecimal("123"));
		ruta1.setDiaFin(new BigDecimal("223"));
		ruta1.setHoraInicio(new BigDecimal("20"));
		ruta1.setHoraFin(new BigDecimal("30"));
		
		ruta2.setId(2);
		ruta2.setNumero("4621");
		ruta2.setDiaInicio(new BigDecimal("223"));
		ruta2.setDiaFin(new BigDecimal("423"));
		ruta2.setHoraInicio(new BigDecimal("21"));
		ruta2.setHoraFin(new BigDecimal("31"));
		
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Ruta[] buses = {ruta1, ruta2};
		
	Mockito
    .when(restTemplate.getForObject(URI_SERVER + "rutas", Tmio1Ruta[].class))
    .thenReturn(buses);

	Iterable<Tmio1Ruta> employee = rutaDelegate.findAll();
    
	assertEquals(ruta1.getId(), employee.iterator().next().getId());

	}
	
	@Test
	public void AgregarBusTest() {
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		
		ruta1.setId(123);
		ruta1.setNumero("1221");
		ruta1.setDiaInicio(new BigDecimal("123"));
		ruta1.setDiaFin(new BigDecimal("223"));
		ruta1.setHoraInicio(new BigDecimal("20"));
		ruta1.setHoraFin(new BigDecimal("30"));
		
	
		Mockito.when(restTemplate.getForObject(URI_SERVER + "rutas/"+ruta1.getId(), Tmio1Ruta.class)).thenReturn(ruta1);

	
	Tmio1Ruta employee;
	try {
		employee = rutaDelegate.findById(ruta1.getId());
		assertEquals(ruta1.getId(), employee.getId());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

	}
	
	
}
