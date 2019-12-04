package co.edu.icesi.ci.talleres.delegates;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
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

import co.edu.icesi.ci.talleres.delegate.BusDelegateImp;
import co.edu.icesi.ci.talleres.delegate.ServicioDelegateImp;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServicioDelegateTest {

	@Mock
	private RestTemplate restTemplate;
	

	@InjectMocks
	@Autowired
	private ServicioDelegateImp busDelegate;
	
	final String URI_SERVER = "http://localhost:8080/api/";
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void MostrarBusTest() {
		Tmio1Servicio serv1 = new Tmio1Servicio();
		Tmio1Servicio serv2 = new Tmio1Servicio();
		Tmio1ServicioPK pk1 = new Tmio1ServicioPK();
		Tmio1ServicioPK pk2 = new Tmio1ServicioPK();
		Tmio1Bus bus1 = new Tmio1Bus();
		Tmio1Bus bus2 = new Tmio1Bus();
		
		bus1.setCapacidad(10.0);
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ADX412");
		bus1.setModelo(2019);
		bus1.setId(123);
		
		bus2.setCapacidad(20.0);
		bus2.setMarca("chevrolet");
		bus2.setPlaca("ADX413");
		bus2.setModelo(2019);
		bus2.setId(456);
		Tmio1Conductore condu1 = new Tmio1Conductore();
		Tmio1Conductore condu2 = new Tmio1Conductore();
		
		condu1.setCedula("12223");
		condu1.setNombre("Beycker");
		condu1.setApellidos("Agredo");
		condu1.setFechaNacimiento(new Date(20));
		condu1.setFechaContratacion(new Date(30));

		condu2.setCedula("22233");
		condu2.setNombre("Narvaez");
		condu2.setApellidos("Alejandro");
		condu2.setFechaNacimiento(new Date(40));
		condu2.setFechaContratacion(new Date(50));
		
		Tmio1Ruta rut1 = new Tmio1Ruta();
		Tmio1Ruta rut2 = new Tmio1Ruta();
		
		rut1.setId(123);
		rut1.setNumero("1221");
		rut1.setDiaInicio(new BigDecimal("123"));
		rut1.setDiaFin(new BigDecimal("223"));
		rut1.setHoraInicio(new BigDecimal("20"));
		rut1.setHoraFin(new BigDecimal("30"));
		
		rut2.setId(323);
		rut2.setNumero("4621");
		rut2.setDiaInicio(new BigDecimal("223"));
		rut2.setDiaFin(new BigDecimal("423"));
		rut2.setHoraInicio(new BigDecimal("21"));
		rut2.setHoraFin(new BigDecimal("31"));
		serv1.setId(pk1);
		serv1.setTmio1Bus(bus1);
		serv1.setTmio1Conductore(condu1);
		serv1.setTmio1Ruta(rut1);
		
		serv2.setId(pk2);
		serv2.setTmio1Bus(bus2);
		serv2.setTmio1Conductore(condu2);
		serv2.setTmio1Ruta(rut2);
		
		
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Servicio[] buses = {serv1,serv1};
		
	Mockito
    .when(restTemplate.getForObject(
    		URI_SERVER + "servicios",Tmio1Servicio[].class))
    .thenReturn(buses);

	Iterable<Tmio1Servicio> employee = busDelegate.getServicios();
    
	assertEquals(serv1.getId(), employee.iterator().next().getId());

	}
	
	@Test
	public void AgregarBusTest() {
		
		Tmio1Servicio serv1 = new Tmio1Servicio();
		Tmio1ServicioPK pk1 = new Tmio1ServicioPK();
		Tmio1Bus bus1 = new Tmio1Bus();
	
		
		bus1.setCapacidad(10.0);
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ADX412");
		bus1.setModelo(2019);
		bus1.setId(123);
	
		Tmio1Conductore condu1 = new Tmio1Conductore();

		
		condu1.setCedula("12223");
		condu1.setNombre("Beycker");
		condu1.setApellidos("Agredo");
		condu1.setFechaNacimiento(new Date(20));
		condu1.setFechaContratacion(new Date(30));

		Tmio1Ruta rut1 = new Tmio1Ruta();
	
		
		rut1.setId(123);
		rut1.setNumero("1221");
		rut1.setDiaInicio(new BigDecimal("123"));
		rut1.setDiaFin(new BigDecimal("223"));
		rut1.setHoraInicio(new BigDecimal("20"));
		rut1.setHoraFin(new BigDecimal("30"));
		
		serv1.setId(pk1);
		serv1.setTmio1Bus(bus1);
		serv1.setTmio1Conductore(condu1);
		serv1.setTmio1Ruta(rut1);
		
//agregar
		Mockito.when( restTemplate.postForEntity(URI_SERVER + "servicios", serv1, Tmio1Servicio.class)).thenReturn(new ResponseEntity<Tmio1Servicio>(serv1, HttpStatus.OK));
	//get
		Mockito.when( restTemplate.getForObject(URI_SERVER + "servicios/" + serv1.getIdHash(), Tmio1Servicio.class)).thenReturn(serv1);

	Tmio1Servicio employee = busDelegate.getServicio(serv1.getIdHash());
    
	assertEquals(serv1.getId(), employee.getId());

	}

	@Test
	public void UpdateBusTest() {
		Tmio1Servicio serv1 = new Tmio1Servicio();
		Tmio1Servicio serv2 = new Tmio1Servicio();
		Tmio1ServicioPK pk1 = new Tmio1ServicioPK();
		Tmio1ServicioPK pk2 = new Tmio1ServicioPK();
		Tmio1Bus bus1 = new Tmio1Bus();
		Tmio1Bus bus2 = new Tmio1Bus();
		
		bus1.setCapacidad(10.0);
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ADX412");
		bus1.setModelo(2019);
		bus1.setId(123);
		
		bus2.setCapacidad(20.0);
		bus2.setMarca("chevrolet");
		bus2.setPlaca("ADX413");
		bus2.setModelo(2019);
		bus2.setId(456);
		Tmio1Conductore condu1 = new Tmio1Conductore();
		Tmio1Conductore condu2 = new Tmio1Conductore();
		
		condu1.setCedula("12223");
		condu1.setNombre("Beycker");
		condu1.setApellidos("Agredo");
		condu1.setFechaNacimiento(new Date(20));
		condu1.setFechaContratacion(new Date(30));

		condu2.setCedula("22233");
		condu2.setNombre("Narvaez");
		condu2.setApellidos("Alejandro");
		condu2.setFechaNacimiento(new Date(40));
		condu2.setFechaContratacion(new Date(50));
		
		Tmio1Ruta rut1 = new Tmio1Ruta();
		Tmio1Ruta rut2 = new Tmio1Ruta();
		
		rut1.setId(123);
		rut1.setNumero("1221");
		rut1.setDiaInicio(new BigDecimal("123"));
		rut1.setDiaFin(new BigDecimal("223"));
		rut1.setHoraInicio(new BigDecimal("20"));
		rut1.setHoraFin(new BigDecimal("30"));
		
		rut2.setId(323);
		rut2.setNumero("4621");
		rut2.setDiaInicio(new BigDecimal("223"));
		rut2.setDiaFin(new BigDecimal("423"));
		rut2.setHoraInicio(new BigDecimal("21"));
		rut2.setHoraFin(new BigDecimal("31"));
		serv1.setId(pk1);
		serv1.setTmio1Bus(bus1);
		serv1.setTmio1Conductore(condu1);
		serv1.setTmio1Ruta(rut1);
		
		serv2.setId(pk2);
		serv2.setTmio1Bus(bus2);
		serv2.setTmio1Conductore(condu2);
		serv2.setTmio1Ruta(rut2);
		
		
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Servicio[] buses = {serv1,serv1};
		
	Mockito
    .when(restTemplate.getForObject(
    		URI_SERVER + "servicios",Tmio1Servicio[].class))
    .thenReturn(buses);
	Tmio1Servicio sevicio=busDelegate.getServicio(serv1.getIdHash());
	Tmio1Servicio sevicio2=busDelegate.getServicio(serv2.getIdHash());

	busDelegate.delServicio(sevicio);
	
	busDelegate.delServicio(sevicio2);
	
	Iterable<Tmio1Servicio> employee = busDelegate.getServicios();
    
	assertNull(employee);
	}
	
}
