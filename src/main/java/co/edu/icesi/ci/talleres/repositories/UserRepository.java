package co.edu.icesi.ci.talleres.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;


public interface UserRepository extends CrudRepository<UserApp, Long> {

	List<UserApp> findByUsername(String username);
	
	List<UserApp> findByType(UserType patient);
}
