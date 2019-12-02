package co.edu.icesi.ci.talleres.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.model.Tmio1Sitio;

public interface SitioRepository extends CrudRepository<Tmio1Sitio, Tmio1ServicioPK> {

}
