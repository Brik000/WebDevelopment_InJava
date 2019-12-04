package co.edu.icesi.ci.talleres.delegates;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Date;

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

import co.edu.icesi.ci.talleres.delegate.ConductorDelegateImpl;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ConductorDelegateTest {

	@Mock
	private RestTemplate restTemplate;
	

	@InjectMocks
	@Autowired
	private ConductorDelegateImpl conductorDelegate;
	
	final String URI_SERVER = "http://localhost:8080/api/";
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void MostrarBusTest() {
		Tmio1Conductore conductor1 = new Tmio1Conductore();
		Tmio1Conductore conductor2 = new Tmio1Conductore();
		
		conductor1.setCedula("2222");
		conductor1.setNombre("Juan");
		conductor1.setApellidos("Lopez");
		conductor1.setFechaNacimiento(LocalDate.now());
		conductor1.setFechaContratacion(LocalDate.of(1999, 12, 20));

		conductor2.setCedula("1111");
		conductor2.setNombre("Santiago");
		conductor2.setApellidos("DelCampo");
		conductor2.setFechaNacimiento(LocalDate.now());
		conductor2.setFechaContratacion(LocalDate.of(1999, 12, 20));
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Conductore[] buses = {conductor1, conductor2};
		
	Mockito
    .when(restTemplate.getForObject(
    		URI_SERVER + "conductores",Tmio1Conductore[].class))
    .thenReturn(buses);

	Iterable<Tmio1Conductore> employee = conductorDelegate.findAll();
    
	assertEquals(conductor1.getCedula(), employee.iterator().next().getCedula());

	}
	
	@Test
	public void AgregarBusTest() {
		
		Tmio1Conductore conductor1 = new Tmio1Conductore();
	
		
		conductor1.setCedula("2222");
		conductor1.setNombre("Juan");
		conductor1.setApellidos("Lopez");
		conductor1.setFechaNacimiento(LocalDate.now());
		conductor1.setFechaContratacion(LocalDate.of(1999, 12, 20));

	Mockito.when( restTemplate.getForObject(URI_SERVER + "conductores/" + conductor1.getCedula(), Tmio1Conductore.class)).thenReturn(conductor1);

	Tmio1Conductore employee;
	try {
		employee = conductorDelegate.findByCedula(conductor1.getCedula());
		assertEquals(conductor1.getCedula(), employee.getCedula());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

	}
	
}
