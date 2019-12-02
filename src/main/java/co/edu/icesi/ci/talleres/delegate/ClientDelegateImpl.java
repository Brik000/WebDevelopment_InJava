package co.edu.icesi.ci.talleres.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;

@Component
public class ClientDelegateImpl implements ClientDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";
	
	public ClientDelegateImpl() {
		restTemplate= new RestTemplate();
	}

	@Override
	public UserApp save(UserApp user) {
		UserApp newCar= restTemplate.postForEntity(SERVER+"users", user, UserApp.class).getBody();
		return newCar;
	}

	@Override
	public UserApp findById(long id) {
		UserApp user= restTemplate.getForObject(SERVER+"users/"+id, UserApp.class);
		return user;
	}

	@Override
	public Iterable<UserApp> findAll() {
		UserApp[] users= restTemplate.getForObject(SERVER+"users", UserApp[].class);
		List<UserApp> at;
		try {
			at= Arrays.asList(users);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<UserApp> findAllAdmins() {
		UserApp[] users= restTemplate.getForObject(SERVER+"admins", UserApp[].class);
		List<UserApp> at;
		List<UserApp> admins= null;
		try {
			at= Arrays.asList(users);
			for(UserApp user: at) {
				if(user.getType().equals(UserType.admin)) {
					admins.add(user);
				}
			}
			return admins;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<UserApp> findAllOperators() {
		UserApp[] users= restTemplate.getForObject(SERVER+"operators", UserApp[].class);
		List<UserApp> at;
		List<UserApp> oper= null;
		try {
			at= Arrays.asList(users);
			for(UserApp user: at) {
				if(user.getType().equals(UserType.operador)) {
					oper.add(user);
				}
			}
			return oper;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(UserApp user) {
		restTemplate.delete(SERVER+"users/"+user.getId());
		
	}

	@Override
	public UserType[] getTypes() {
		UserApp[] users= restTemplate.getForObject(SERVER+"operators", UserApp[].class);
		List<UserApp> at;
		List<UserType> tp=null;
		UserType[] types= new UserType[users.length];
		try {
			at= Arrays.asList(users);
			for(UserApp user: at) {
				tp.add(user.getType());
			}
			types= (UserType[]) tp.toArray();
			return types;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
