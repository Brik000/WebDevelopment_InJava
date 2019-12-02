package co.edu.icesi.ci.talleres.dao;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.talleres.Ci192TalleresApplication;
import co.edu.icesi.ci.talleres.dao.IConsultBusIn;
import co.edu.icesi.ci.talleres.dao.IConsultConductoresIn;
import co.edu.icesi.ci.talleres.dao.IConsultRutasIn;
import co.edu.icesi.ci.talleres.dao.IConsultServicesIn;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import org.junit.runners.MethodSorters;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Ci192TalleresApplication.class)
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConductoreDAO {
	@Autowired
	private IConsultConductoresIn conductoreDao;
	
	@Autowired
	private IConsultBusIn busDao;
	
	@Autowired
	private IConsultRutasIn rutaDao;
	
	
	@Autowired
	private IConsultServicesIn serviceDao;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {
		assertNotNull(conductoreDao);
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.now());
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		
		conductoreDao.save(newConductore);
		
		Tmio1Conductore newConductore2 = new Tmio1Conductore();
		newConductore2.setApellidos("Caicedo");
		newConductore2.setCedula("16356137");
		newConductore2.setNombre("Camilo");
		newConductore2.setFechaContratacion(LocalDate.now());
		newConductore2.setFechaNacimiento(LocalDate.of(1999, 12, 9));
	
		conductoreDao.save(newConductore2);
		
		Tmio1Conductore newConductore3 = new Tmio1Conductore();
		newConductore3.setApellidos("Mabesoy");
		newConductore3.setCedula("158854499");
		newConductore3.setNombre("Julian");
		newConductore3.setFechaContratacion(LocalDate.now());
		newConductore3.setFechaNacimiento(LocalDate.of(1999, 12, 9));
	
		conductoreDao.save(newConductore3);
		
		Tmio1Ruta newRuta= new Tmio1Ruta();
		newRuta.setActiva("True");
		newRuta.setDescripcion("Troncal Lenta");
		newRuta.setDiaFin(new BigDecimal(25));
		newRuta.setDiaInicio(new BigDecimal(2));
		newRuta.setHoraFin(new BigDecimal(18));
		newRuta.setHoraInicio(new BigDecimal(6));
		newRuta.setNumero("T31");
		rutaDao.save(newRuta);
		
		Tmio1Ruta newRuta2= new Tmio1Ruta();
		newRuta2.setActiva("True");
		newRuta2.setDescripcion("Expreso Rapido");
		newRuta2.setDiaFin(new BigDecimal(25));
		newRuta2.setDiaInicio(new BigDecimal(2));
		newRuta2.setHoraFin(new BigDecimal(18));
		newRuta2.setHoraInicio(new BigDecimal(6));
		newRuta2.setNumero("E31");
		rutaDao.save(newRuta2);
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes");
		bus1.setModelo(new BigDecimal(2017));
		bus1.setPlaca("HEL546");
		bus1.setTipo("A");
		
		busDao.save(bus1);
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10000));
		bus2.setMarca("Mercedes");
		bus2.setModelo(new BigDecimal(2012));
		bus2.setPlaca("DIE312");
		bus2.setTipo("P");
		
		busDao.save(bus2);
		
		Tmio1ServicioPK pk= new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// mercedes 1
		pk.setIdRuta(newRuta.getId()); // ruta troncal 1
		Tmio1Servicio newService= new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(newRuta);
		
		serviceDao.save(newService);

		Tmio1ServicioPK pk2= new Tmio1ServicioPK();
		pk2.setCedulaConductor(newConductore2.getCedula());// gallo
		pk2.setFechaFin(LocalDate.of(2016, 12, 9));
		pk2.setFechaInicio(LocalDate.of(2015, 12, 9));
		pk2.setIdBus(bus1.getId());// mercedes 1
		pk2.setIdRuta(newRuta.getId()); // ruta troncal 1
		
		Tmio1Servicio newService2= new Tmio1Servicio();
		newService2.setId(pk2);
		newService2.setTmio1Bus(bus1);
		newService2.setTmio1Conductore(newConductore2);
		newService2.setTmio1Ruta(newRuta);
		
		serviceDao.save(newService2);
		
		Tmio1ServicioPK pk3= new Tmio1ServicioPK();
		pk3.setCedulaConductor(newConductore3.getCedula());// gallo
		pk3.setFechaFin(LocalDate.of(2019, 12, 9));
		pk3.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk3.setIdBus(bus1.getId());// mercedes 1
		pk3.setIdRuta(newRuta.getId()); // ruta troncal 1
		
		Tmio1Servicio newService3= new Tmio1Servicio();
		newService3.setId(pk3);
		newService3.setTmio1Bus(bus1);
		newService3.setTmio1Conductore(newConductore3);
		newService3.setTmio1Ruta(newRuta);

		serviceDao.save(newService3);
		
	}
	@Test
	public void testSave() {	
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Camilo");
		driver.setApellidos("Lopez");
		driver.setCedula("959595");
		driver.setFechaContratacion(LocalDate.of(2019, 06, 18));
		driver.setFechaNacimiento(LocalDate.of(1997, 01, 28));
		
		try {
			conductoreDao.save(driver);
		} catch (Exception e) {
			fail("It's not saving");
		}	
		Tmio1Conductore driverS = conductoreDao.findById("959595");
		assertEquals(driver.getCedula(),driverS.getCedula());
		assertEquals(driver.getNombre(), driverS.getNombre());
		assertEquals(driver.getApellidos(), driverS.getApellidos());
		assertTrue(driver.getFechaNacimiento().compareTo(driverS.getFechaNacimiento()) == 0);
		assertTrue(driver.getFechaContratacion().compareTo(driverS.getFechaContratacion()) == 0);
	}
	@Test
	public void testFindByName() {
		List<Tmio1Conductore> conductores= conductoreDao.findbyNombre("Juan");
		try {
			for (int i = 0; i < conductores.size(); i++) {
				Tmio1Conductore driver = conductores.get(i);
				assertEquals(driver.getNombre(), "Juan");
			}
		} catch (Exception e) {
			fail("Not found!");
		}	
	}
	
	@Test
	public void testFindByApellido() {
		List<Tmio1Conductore> conductores= conductoreDao.findbyApellidos("Gallo");
		try {
			for (int i = 0; i < conductores.size(); i++) {
				Tmio1Conductore driver = conductores.get(i);
				assertEquals(driver.getApellidos(), "Gallo");
			}
		} catch (Exception e) {
			fail("Not found!");
		}	
	}
	
	@Test
	public void testByFechaEspecifica() {
		List<Tmio1Conductore> conductores= conductoreDao.consultaFechaEspecifica(LocalDate.of(2017, 12, 24).toString());
		assertTrue(conductores.get(0).getNombre().equals("Julian"));
		assertTrue(conductores.get(1).getNombre().equals("Juan"));
	}
	@Test
	public void testFindAll() {
		List<Tmio1Conductore> conductores= conductoreDao.findAll();
		assertTrue(conductores.size()==3);
		assertTrue(conductores.get(0).getNombre().equals("Juan"));
		assertTrue(conductores.get(1).getNombre().equals("Camilo"));
		assertTrue(conductores.get(2).getNombre().equals("Julian"));
	}
	
}
