package co.edu.icesi.ci.talleres.delegate;


import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;

public interface ClientDelegate {
	
	public UserApp save(UserApp user);

	public UserApp findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public void delete(UserApp user);

	public UserType[] getTypes();

}
