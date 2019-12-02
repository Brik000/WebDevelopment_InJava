package co.edu.icesi.ci.talleres.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.talleres.Ci192TalleresApplication;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Ci192TalleresApplication.class)
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBusDAO {
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
		pk2.setCedulaConductor(newConductore2.getCedula());
		pk2.setFechaFin(LocalDate.of(2018, 12, 9));
		pk2.setFechaInicio(LocalDate.of(2015, 12, 9));
		pk2.setIdBus(bus1.getId());
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
		pk3.setIdBus(bus2.getId());// mercedes 1
		pk3.setIdRuta(newRuta.getId()); // ruta troncal 1
		
		Tmio1Servicio newService3= new Tmio1Servicio();
		newService3.setId(pk3);
		newService3.setTmio1Bus(bus2);
		newService3.setTmio1Conductore(newConductore3);
		newService3.setTmio1Ruta(newRuta);

		serviceDao.save(newService3);
		
	}
	@Test
	public void testFindbyid() {
		
		List<Tmio1Bus> bus= busDao.findbyid(1);
		assertTrue(bus.get(0).getMarca().equals("Mercedes"));
		assertTrue(bus.get(0).getPlaca().equals("HEL546"));
		assertTrue(bus.get(0).getTipo().equals("A"));
	}
	@Test
	public void testFindAll() {
		List<Tmio1Bus> buses= busDao.findAll();
		assertTrue(buses.size()==2);
		assertTrue(buses.get(0).getPlaca().equals("HEL546"));
		assertTrue(buses.get(1).getPlaca().equals("DIE312"));
	}
	@Test
	public void testSave() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(3200));
		bus.setMarca("Mercedes");
		bus.setModelo(new BigDecimal(2012));
		bus.setPlaca("USK856");
		bus.setTipo("A");	
		try {
			busDao.save(bus);
		} catch (Exception e) {
			fail();
		}
		Tmio1Bus buscar= busDao.findbyPlaca("USK856").get(0);
		assertTrue(buscar.getCapacidad().compareTo(bus.getCapacidad())==0);
		assertTrue(buscar.getMarca().equals(bus.getMarca()));
		assertTrue(buscar.getModelo().compareTo(bus.getModelo())==0);
		assertTrue(buscar.getTipo().equals(bus.getTipo()));
	}
	@Test
	public void testFindByPlaca() {
		List<Tmio1Bus> buscar= busDao.findbyPlaca("HEL546");
		assertTrue(buscar.size()==1);
		assertTrue(buscar.get(0).getPlaca().equals("HEL546"));
		assertTrue(buscar.get(0).getMarca().equals("Mercedes"));
	}
	@Test
	public void testFindByMarca() {
		List<Tmio1Bus> buscar= busDao.findbyMarca("Mercedes");
		assertTrue(buscar.size()==2);
		assertTrue(buscar.get(0).getPlaca().equals("HEL546"));
		assertTrue(buscar.get(0).getMarca().equals("Mercedes"));
		assertTrue(buscar.get(1).getPlaca().equals("DIE312"));
		assertTrue(buscar.get(1).getMarca().equals("Mercedes"));
	}
	@Test
	public void testFindByModelo() {
		List<Tmio1Bus> buscar= busDao.findbyModelo(new BigDecimal("2017"));
		assertTrue(buscar.size()==1);
		assertTrue(buscar.get(0).getPlaca().equals("HEL546"));
		assertTrue(buscar.get(0).getMarca().equals("Mercedes"));
	}
	@Test
	public void testSameDay() {
		List<Tmio1Bus> buscar= busDao.sameServiceDay(LocalDate.of(2018, 5, 10).toString());
		for (int i = 0; i < buscar.size(); i++) {
			assertTrue(buscar.get(i).getPlaca().equals("HEL546"));
		}
	}

}
