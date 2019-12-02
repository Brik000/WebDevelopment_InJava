package co.edu.icesi.ci.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;
import co.edu.icesi.ci.talleres.services.UserService;

@RestController
public class ClientRestControllerImpl implements ClientRestController{

	@Autowired
	private UserService service;
	
	@Override
	@PostMapping("/users")
	public UserApp save(@RequestBody UserApp user) {
		service.save(user);
		return user;
	}

	@Override
	@GetMapping("/users/{id}")
	public Optional<UserApp> findById(@PathVariable long id) {
		// TODO Auto-generated method stub
		return service.findById(id);
	}

	@Override
	@GetMapping("/users")
	public Iterable<UserApp> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	@GetMapping("/admins")
	public Iterable<UserApp> findAllAdmins() {
		// TODO Auto-generated method stub
		return service.findAllAdmins();
	}

	@Override
	@GetMapping("/operators")
	public Iterable<UserApp> findAllOperators() {
		// TODO Auto-generated method stub
		return service.findAllOperators();
	}

	@Override
	@DeleteMapping("/users/{id}")
	public UserApp delete(UserApp user) {
		Optional<UserApp> newUser = service.findById(user.getId());
		service.delete(newUser.get());
		return newUser.get();
	}

	@Override
	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return service.getTypes();
	}

}
