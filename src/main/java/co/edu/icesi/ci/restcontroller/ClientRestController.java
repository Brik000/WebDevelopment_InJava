package co.edu.icesi.ci.restcontroller;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;

public interface ClientRestController {
	
	public UserApp save(UserApp user);

	public UserApp findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public UserApp delete(UserApp user);

	public UserType[] getTypes();

}
