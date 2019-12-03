package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.talleres.dao.IBusDao;
import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@SpringBootTest
public class TestBus {
	
	@Autowired
	private IBusDao busDao;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		
		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
			
		busDao.save(bus);

		assertEquals(1, busDao.findAll().size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {
		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
		
		busDao.save(bus);
		
		busDao.delete(bus);
		
		assertEquals(0, busDao.findAll().size());;
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {
		
		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
		busDao.save(bus);
		
		bus.setMarca("Ferrari");
		bus.setTipo(BusType.E);
		
		busDao.update(bus);
		
		assertEquals(0, busDao.findByMarca("Porsche").size());

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		
		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
		
		Tmio1Bus bus1=new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(56));
		bus1.setMarca("Ferrari");
		bus1.setModelo(new BigDecimal(718));
		bus1.setPlaca("BZB-473");
		bus1.setTipo(BusType.E);
		
		busDao.save(bus);
		busDao.save(bus1);
		
		assertEquals(2,busDao.findAll().size());

		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlaca() {
		

		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
		busDao.save(bus);
		
		Tmio1Bus a=busDao.findByPlaca("HGQ-400").get(0);
		
		assertEquals(bus.getId(),a.getId());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMarca() {
		

		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
		busDao.save(bus);
		
		Tmio1Bus a=busDao.findByMarca("Porsche").get(0);
		
		assertEquals(bus.getId(),a.getId());
		
	}
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModelo() {
		

		Tmio1Bus bus=new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(56));
		bus.setMarca("Porsche");
		bus.setModelo(new BigDecimal(911));
		bus.setPlaca("HGQ-400");
		bus.setTipo(BusType.E);
		busDao.save(bus);
		
		Tmio1Bus a=busDao.findByModelo(new BigDecimal(911)).get(0);
		
		assertEquals(bus.getId(),a.getId());
		
	}
	
	
	
	
}
