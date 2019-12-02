package co.edu.icesi.ci.talleres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;
import co.edu.icesi.ci.talleres.repositories.UserRepository;

@Service
public class UserService implements UserServiceInt{

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save(UserApp user) {
		userRepository.save(user);
	}

	public Optional<UserApp> findById(long id) {
		return userRepository.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}
	
	public Iterable<UserApp> findAllAdmins() {
		return userRepository.findByType(UserType.admin);
	}
	
	public Iterable<UserApp> findAllOperators() {
		return userRepository.findByType(UserType.operador);
	}

	public void delete(UserApp user) {
		userRepository.delete(user);
	}
	public UserType[] getTypes() {
		return UserType.values();
	}
}
