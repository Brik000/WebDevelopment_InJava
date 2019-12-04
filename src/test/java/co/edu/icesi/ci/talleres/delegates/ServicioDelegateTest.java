package co.edu.icesi.ci.talleres.delegates;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
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

import co.edu.icesi.ci.talleres.delegate.ServiceDelegateImpl;
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
	private ServiceDelegateImpl serviceDelegate;
	
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
		
		bus1.setCapacidad(BigDecimal.valueOf(10.0));
		bus1.setMarca("volvo");
		bus1.setPlaca("hzv123");
		bus1.setModelo(BigDecimal.valueOf(2019));
		bus1.setId(123);
		
		
		bus2.setCapacidad(BigDecimal.valueOf(20.0));
		bus2.setMarca("mercedez");
		bus2.setPlaca("kds863");
		bus2.setModelo(BigDecimal.valueOf(2019));
		bus2.setId(456);
		
		Tmio1Conductore conductor1= new Tmio1Conductore();
		Tmio1Conductore conductor2= new Tmio1Conductore();
		
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
		serv1.setTmio1Conductore(conductor1);
		serv1.setTmio1Ruta(rut1);
		
		serv2.setId(pk2);
		serv2.setTmio1Bus(bus2);
		serv2.setTmio1Conductore(conductor2);
		serv2.setTmio1Ruta(rut2);
		
		
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Servicio[] buses = {serv1,serv1};
		
	Mockito
    .when(restTemplate.getForObject(
    		URI_SERVER + "servicios",Tmio1Servicio[].class))
    .thenReturn(buses);

	Iterable<Tmio1Servicio> employee = serviceDelegate.findAllServices();
    
	assertEquals(serv1.getId(), employee.iterator().next().getId());

	}
	
	@Test
	public void AgregarBusTest() {
		
		Tmio1Servicio serv1 = new Tmio1Servicio();
		Tmio1ServicioPK pk1 = new Tmio1ServicioPK();
		Tmio1Bus bus1 = new Tmio1Bus();
	
		
		bus1.setCapacidad(BigDecimal.valueOf(10.0));
		bus1.setMarca("volvo");
		bus1.setPlaca("hzv123");
		bus1.setModelo(BigDecimal.valueOf(2019));
		bus1.setId(123);
	
		Tmio1Conductore conductor1 = new Tmio1Conductore();

		
		conductor1.setCedula("2222");
		conductor1.setNombre("Juan");
		conductor1.setApellidos("Lopez");
		conductor1.setFechaNacimiento(LocalDate.now());
		conductor1.setFechaContratacion(LocalDate.of(1999, 12, 20));

		Tmio1Ruta rut1 = new Tmio1Ruta();
	
		
		rut1.setId(123);
		rut1.setNumero("1221");
		rut1.setDiaInicio(new BigDecimal("123"));
		rut1.setDiaFin(new BigDecimal("223"));
		rut1.setHoraInicio(new BigDecimal("20"));
		rut1.setHoraFin(new BigDecimal("30"));
		
		serv1.setId(pk1);
		serv1.setTmio1Bus(bus1);
		serv1.setTmio1Conductore(conductor1);
		serv1.setTmio1Ruta(rut1);
		
//agregar
	//get
		Mockito.when( restTemplate.getForObject(URI_SERVER + "servicios/" + serv1.getId(), Tmio1Servicio.class)).thenReturn(serv1);

	Tmio1Servicio employee = serviceDelegate.findById(serv1.getId());
    
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
		
		bus1.setCapacidad(BigDecimal.valueOf(10.0));
		bus1.setMarca("volvo");
		bus1.setPlaca("hzv123");
		bus1.setModelo(BigDecimal.valueOf(2019));
		bus1.setId(123);
		
		bus2.setCapacidad(BigDecimal.valueOf(20.0));
		bus2.setMarca("mercedez");
		bus2.setPlaca("kds863");
		bus2.setModelo(BigDecimal.valueOf(2019));
		bus2.setId(456);
		
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
		serv1.setTmio1Conductore(conductor1);
		serv1.setTmio1Ruta(rut1);
		
		serv2.setId(pk2);
		serv2.setTmio1Bus(bus2);
		serv2.setTmio1Conductore(conductor2);
		serv2.setTmio1Ruta(rut2);
		
		
		//Yo le doy comportamiento a los metodos del RestTemplate
		Tmio1Servicio[] buses = {serv1,serv1};
		
	Mockito
    .when(restTemplate.getForObject(
    		URI_SERVER + "servicios",Tmio1Servicio[].class))
    .thenReturn(buses);
	Tmio1Servicio sevicio=serviceDelegate.findById(serv1.getId());
	Tmio1Servicio sevicio2=serviceDelegate.findById(serv2.getId());

	
	
	Iterable<Tmio1Servicio> employee = serviceDelegate.findAllServices();
    
	assertNull(employee);
	}
	
}
