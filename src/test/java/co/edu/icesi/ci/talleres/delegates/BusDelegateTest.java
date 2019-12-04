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

import co.edu.icesi.ci.talleres.delegate.BusesDelegateImpl;
import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BusDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	

	@InjectMocks
	@Autowired
	private BusesDelegateImpl busDelegate;
	
	final String URI_SERVER = "http://localhost:8080/api/";
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void FindAllTest() {
		Tmio1Bus bus1 = new Tmio1Bus();
		Tmio1Bus bus2 = new Tmio1Bus();
		
		bus1.setCapacidad(BigDecimal.valueOf(10.0));
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ADX412");
		bus1.setModelo(BigDecimal.valueOf(2019));
		bus1.setId(123);
		
		bus2.setCapacidad(BigDecimal.valueOf(20.0));
		bus2.setMarca("chevrolet");
		bus2.setPlaca("ADX413");
		bus2.setModelo(BigDecimal.valueOf(2019));
		bus2.setId(456);

		Tmio1Bus[] buses = {bus1, bus2};
		
	Mockito
    .when(restTemplate.getForObject(
    		URI_SERVER + "buses",Tmio1Bus[].class))
    .thenReturn(buses);

	Iterable<Tmio1Bus> employee = busDelegate.findAll();
    
	assertEquals(bus1.getId(), employee.iterator().next().getId());

	}
	
	
	@Test
	public void SaveBusTest() {
		Tmio1Bus bus1 = new Tmio1Bus();

		bus1.setCapacidad(BigDecimal.valueOf(10.0));
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ADX412");
		bus1.setModelo(BigDecimal.valueOf(2019));
		bus1.setId(123);	

	Mockito.when( restTemplate.getForObject(URI_SERVER + "buses/" + bus1.getId(), Tmio1Bus.class)).thenReturn(bus1);

	Tmio1Bus employee;
	try {
		employee = busDelegate.findById(bus1.getId());
		assertEquals(bus1.getId(), employee.getId());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

	}
	
	@Test
	public void testobtenerTipos () {
		
		BusType[] tiposBus = new BusType[3];
		tiposBus[0]=BusType.E;
		tiposBus[1]=BusType.T;
		tiposBus[2]=BusType.P;
		
		Mockito.when(restTemplate.getForObject(URI_SERVER+"buses/types", BusType[].class)).thenReturn(tiposBus);
		assertEquals(busDelegate.getTypes(), tiposBus);
	}


	}
	

