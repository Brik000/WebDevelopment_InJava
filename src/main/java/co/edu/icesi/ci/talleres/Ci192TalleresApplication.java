package co.edu.icesi.ci.talleres;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.talleres.dao.BusDao;
import co.edu.icesi.ci.talleres.dao.ConductorDao;
import co.edu.icesi.ci.talleres.dao.RutaDao;
import co.edu.icesi.ci.talleres.dao.ServicioDao;
import co.edu.icesi.ci.talleres.model.BusType;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;
import co.edu.icesi.ci.talleres.repositories.BusesRepository;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;
import co.edu.icesi.ci.talleres.repositories.ServiciosRepository;
import co.edu.icesi.ci.talleres.repositories.UserRepository;

@SpringBootApplication
public class Ci192TalleresApplication {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Ci192TalleresApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner demo(UserRepository userRepository,BusDao busRepository, RutaDao rutaRepository, ConductorDao conductoresRepository, ServicioDao serviceRepository) {
		return (args) -> {
			UserApp user = new UserApp();			
			user.setUsername("admin");
			user.setPassword("123");
			user.setType(UserType.admin);
			userRepository.save(user);
			
			UserApp user2 = new UserApp();			
			user2.setUsername("campo");
			user2.setPassword("123");
			user2.setType(UserType.operador);
			userRepository.save(user2);
			
			Tmio1Bus bus= new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(44));
			bus.setMarca("Nothing");
			bus.setModelo(new BigDecimal(44));
			bus.setPlaca("HEL546");
			bus.setTipo(BusType.E);
			busRepository.save(bus);
			
			Tmio1Conductore c= new Tmio1Conductore();
			c.setApellidos("delcampo");
			c.setCedula("123");
			c.setFechaContratacion(LocalDate.now());
			c.setFechaNacimiento(LocalDate.of(1999, 12, 20));
			c.setNombre("Santiago");
			conductoresRepository.save(c);
			
			Tmio1Ruta ruta= new Tmio1Ruta();
			ruta.setActiva("Si");
			ruta.setDescripcion("Nothing");
			ruta.setDiaFin(new BigDecimal(2));
			ruta.setDiaInicio(new BigDecimal(33));
			ruta.setHoraFin(new BigDecimal(22));
			ruta.setHoraInicio(new BigDecimal(2));
			ruta.setNumero("22");
			rutaRepository.save(ruta);
			
			Tmio1Servicio s= new Tmio1Servicio();
			s.setTmio1Bus(bus);
			s.setTmio1Conductore(c);
			s.setTmio1Ruta(ruta);
			Tmio1ServicioPK aux= new Tmio1ServicioPK();
			aux.setCedulaConductor("123");
			aux.setFechaInicio(LocalDate.of(2019, 11, 20));
			aux.setFechaFin(LocalDate.of(2019, 12, 20));
			aux.setIdBus(1);
			aux.setIdRuta(1);
			
			s.setId(aux);
			
			
			
			Tmio1Servicio s2= new Tmio1Servicio();
			s2.setTmio1Bus(bus);
			s2.setTmio1Conductore(c);
			s2.setTmio1Ruta(ruta);
			Tmio1ServicioPK aux2= new Tmio1ServicioPK();
			aux2.setCedulaConductor("123");
			aux2.setFechaInicio(LocalDate.of(2019, 10, 20));
			aux2.setFechaFin(LocalDate.of(2019, 9, 20));
			aux2.setIdBus(1);
			aux2.setIdRuta(1);
			
			s2.setId(aux2);
			serviceRepository.save(s2);
			
			};
		}
}
