package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.IConsultConductoresIn;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;

@Service
public class ConductorService implements ConductorServiceIn{
	@Autowired
	//private ConductoresRepository conductorRepository;
	private IConsultConductoresIn conductorRepository;
	
	@Autowired
	public ConductorService(IConsultConductoresIn conductorRepository) {
		this.conductorRepository= conductorRepository;
	}
	
	@Override
	@Transactional
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
		return Optional.of(conductorRepository.findById(cedula));
	}
	public void setRepository(IConsultConductoresIn conductoRepository) {
		this.conductorRepository= conductoRepository;
	}
	public Iterable<Tmio1Conductore> findAll() {
		return conductorRepository.findAll();
	}
	public void delete(Tmio1Conductore user) {
		conductorRepository.delete(user);
	}

}
