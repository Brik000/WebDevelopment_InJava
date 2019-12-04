package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.repositories.ConductoresRepository;

@Service
public class ConductorService implements ConductorServiceIn{

	private ConductoresRepository conductorRepository;
	
	@Autowired
	public ConductorService(ConductoresRepository conductorRepository) {
		this.conductorRepository= conductorRepository;
	}
	
	@Override
	public void saveConductor(Tmio1Conductore conductor) {
		conductorRepository.save(conductor);
	}

	@Override
	public void validarConductor(Tmio1Conductore conductor) throws Exception {
		if(conductor.getFechaContratacion().isBefore(conductor.getFechaNacimiento())) {
			throw new Exception("Fecha invalida");
		}
	}
	@Override
	public Optional<Tmio1Conductore> findByCedula(String cedula) throws Exception {
		return conductorRepository.findById(cedula);
	}
	@Override
	public void setRepository(ConductoresRepository conductoRepository) {
		this.conductorRepository= conductoRepository;
	}
	@Override
	public Iterable<Tmio1Conductore> findAll() {
		return conductorRepository.findAll();
	}
	@Override
	public void delete(Tmio1Conductore user) {
		conductorRepository.delete(user);
	}

}
