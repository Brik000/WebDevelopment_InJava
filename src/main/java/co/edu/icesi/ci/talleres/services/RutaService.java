package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.repositories.RutasRepository;

@Service
public class RutaService implements RutaServiceIn{
	private RutasRepository rutaRepository;
	@Autowired
	public RutaService(RutasRepository rutaRepository) {
		this.rutaRepository= rutaRepository;
	}
	@Override
	public void saveRuta(Tmio1Ruta ruta) {
		rutaRepository.save(ruta);
	}
	@Override
	public void validarRuta(Tmio1Ruta ruta) throws Exception {
		if(ruta.getHoraFin().compareTo(ruta.getHoraInicio())==-1) {
			throw new Exception("La hora final no puede ser menor a la inicial");
		}
		if(ruta.getDiaFin().compareTo(ruta.getDiaInicio())==-1) {
			throw new Exception("La fecha no es valida, el dia de inicio debe ser mayor a la fecha final");
		}
	}
	@Override
	public Optional<Tmio1Ruta> findById(int id) throws Exception {
		return rutaRepository.findById(id);
	}
	public void setRepository(RutasRepository rutaRepository) {
		this.rutaRepository= rutaRepository;
	}
	
	public Iterable<Tmio1Ruta> findAll() {
		return rutaRepository.findAll();
	}
	public void delete(Tmio1Ruta user) {
		rutaRepository.delete(user);
	}
}
