package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.talleres.dao.IConductorDao;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@SpringBootTest
public class TestConductores {
	
	
	@Autowired
	private IConductorDao conductDao;
	
	public void escenario() {
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Santiago");
		driver.setApellidos("del Campo");
		driver.setCedula("1144103822");
		driver.setFechaContratacion(LocalDate.of(2018, 06, 12));
		driver.setFechaNacimiento(LocalDate.of(1998, 01, 25));
		conductDao.save(driver);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Santiago");
		driver.setApellidos("del Campo");
		driver.setCedula("1144103822");
		driver.setFechaContratacion(LocalDate.of(2018, 06, 12));
		driver.setFechaNacimiento(LocalDate.of(1998, 01, 25));
		
		try {
			conductDao.save(driver);
		} catch (Exception e) {
			// TODO: handle exception
			fail("It's not saving");
		}
		
		Tmio1Conductore driverS = conductDao.findByNombre("Santiago").get(0);
		assertEquals(driver.getCedula(),driverS.getCedula());
		assertEquals(driver.getNombre(), driverS.getNombre());
		assertEquals(driver.getApellidos(), driverS.getApellidos());
		assertTrue(driver.getFechaNacimiento().compareTo(driverS.getFechaNacimiento()) == 0);
		assertTrue(driver.getFechaContratacion().compareTo(driverS.getFechaContratacion()) == 0);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Santiago");
		driver.setApellidos("del Campo");
		driver.setCedula("1144103822");
		driver.setFechaContratacion(LocalDate.of(2018, 06, 12));
		driver.setFechaNacimiento(LocalDate.of(1998, 01, 25));
		conductDao.save(driver);
		conductDao.delete(conductDao.findByNombre("Santiago").get(0));
		
		assertEquals(0, conductDao.findAll().size());;
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buscarNombreTest() {
		this.escenario();
		assertNotNull(conductDao.findByNombre("Santiago").get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buscarApellidoTest() {
		this.escenario();
		assertNotNull(conductDao.findByApellido("del Campo").get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Santiago");
		driver.setApellidos("del Campo");
		driver.setCedula("1144103822");
		driver.setFechaContratacion(LocalDate.of(2018, 06, 12));
		driver.setFechaNacimiento(LocalDate.of(1998, 01, 25));
		conductDao.save(driver);
		driver.setApellidos("jose");
		driver.setNombre("criollo");
		conductDao.update(driver);
		
		assertEquals(0, conductDao.findByNombre("Santiago").size());

	}	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Santiago");
		driver.setApellidos("del Campo");
		driver.setCedula("1144103822");
		driver.setFechaContratacion(LocalDate.of(2018, 06, 12));
		driver.setFechaNacimiento(LocalDate.of(1998, 01, 25));
		
		
		Tmio1Conductore driver2 = new Tmio1Conductore();
		driver2.setNombre("jose");
		driver2.setApellidos("diaz");
		driver2.setCedula("11234532");
		driver2.setFechaContratacion(LocalDate.of(2018, 06, 12));
		driver2.setFechaNacimiento(LocalDate.of(1998, 01, 25));
	
		conductDao.save(driver);
		conductDao.save(driver2);
		
		
		assertEquals(2,conductDao.findAll().size());
	
	
	}
	

}
