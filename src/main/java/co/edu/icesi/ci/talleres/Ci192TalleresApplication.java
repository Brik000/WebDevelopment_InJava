package co.edu.icesi.ci.talleres;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

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
	public CommandLineRunner demo(UserRepository userRepository,BusesRepository busRepository, RutasRepository rutaRepository, ConductoresRepository conductoresRepository, ServiciosRepository serviceRepository) {
		return (args) -> {
			UserApp user = new UserApp();			
			user.setUsername("admin");
			user.setPassword("admin");
			user.setType(UserType.admin);
			userRepository.save(user);
			
			UserApp user2 = new UserApp();			
			user2.setUsername("opr");
			user2.setPassword("opr");
			user2.setType(UserType.operador);
			userRepository.save(user2);
//			
//			
//			Tmio1Conductore c= new Tmio1Conductore();
//			c.setApellidos("Carlos");
//			c.setCedula("1104820995");
//			c.setFechaContratacion(LocalDate.now());
//			c.setFechaNacimiento(LocalDate.of(1985, 10, 15));
//			c.setNombre("Santiago");
//			conductoresRepository.save(c);
//			
//			Tmio1Bus bus= new Tmio1Bus();
//			bus.setCapacidad(new BigDecimal(44));
//			bus.setMarca("Volvo");
//			bus.setModelo(new BigDecimal(44));
//			bus.setPlaca("JKM-222");
//			bus.setTipo("T");
//			bus.setId(1);
//			busRepository.save(bus);
//			
//			Tmio1Ruta ruta= new Tmio1Ruta();
//			ruta.setActiva("Medio dia");
//			ruta.setDescripcion("Ruta hasta el sur");
//			ruta.setDiaFin(new BigDecimal(25));
//			ruta.setDiaInicio(new BigDecimal(2));
//			ruta.setHoraFin(new BigDecimal(19));
//			ruta.setHoraInicio(new BigDecimal(9));
//			ruta.setId(1);
//			ruta.setNumero("T31");
//			rutaRepository.save(ruta);
//			
//			Tmio1Servicio s= new Tmio1Servicio();
//			s.setTmio1Bus(bus);
//			s.setTmio1Conductore(c);
//			s.setTmio1Ruta(ruta);
//			Tmio1ServicioPK aux= new Tmio1ServicioPK();
//			aux.setCedulaConductor("1104820995");
//			aux.setFechaInicio(LocalDate.of(2019, 01, 01));
//			aux.setFechaFin(LocalDate.of(2019, 12, 24));
//			aux.setIdBus(1);
//			aux.setIdRuta(1);
//			
//			s.setId(aux);
//			serviceRepository.save(s);
//			
//			Tmio1Servicio service2= new Tmio1Servicio();
//			service2.setTmio1Bus(bus);
//			service2.setTmio1Conductore(c);
//			service2.setTmio1Ruta(ruta);
//			
//			Tmio1ServicioPK id2= new Tmio1ServicioPK();
//			id2.setCedulaConductor("1104820995");
//			id2.setFechaInicio(LocalDate.of(2019, 01, 01));
//			id2.setFechaFin(LocalDate.of(2019, 12, 24));
//			id2.setIdBus(1);
//			id2.setIdRuta(1);
//			
//			
//			service2.setId(id2);
//			
//			serviceRepository.save(service2);
//			
			};
		}
}
