package co.edu.icesi.ci.talleres.services;


import java.util.Optional;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;


public interface UserServiceInt {
	
	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public void delete(UserApp user);

	public UserType[] getTypes();
	
	
}
