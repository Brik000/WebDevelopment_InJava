package co.edu.icesi.ci.talleres.delegates;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.ci.talleres.delegate.ClientDelegateImpl;
import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;



//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Log
public class TestClientDelegate {
	
	@InjectMocks
	private ClientDelegateImpl clidel;
	
	@Mock
	private RestTemplate rest;
	
	
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testBusSave() {
		
		UserApp user=new UserApp();
		user.setId(0);
		user.setPassword("123");
		user.setType(UserType.admin);
		user.setUsername("Santiago");
		
		UserApp user1=new UserApp();
		user1.setId(1);
		user1.setPassword("123");
		user1.setType(UserType.operador);
		user1.setUsername("jose");
		
		
		ArrayList<UserApp> real=new ArrayList<UserApp>();
		
		real.add(user1);
		real.add(user);
		
		ResponseEntity<UserApp> tb = new ResponseEntity<UserApp>(user, HttpStatus.ACCEPTED);

		when(rest.postForEntity(clidel.SERVER + "users", user, UserApp.class)).thenReturn(tb);
		
		
		UserApp x = clidel.save(user);
		UserApp expected = user;

		assertTrue(x.equals(expected));

	}
	
	@Test
	public void testBusFindById() {
		UserApp user=new UserApp();
		user.setId(0);
		user.setPassword("123");
		user.setType(UserType.admin);
		user.setUsername("Santiago");
		
		ResponseEntity<UserApp> tb = new ResponseEntity<UserApp>(user, HttpStatus.ACCEPTED);
		
		when(rest.postForEntity(clidel.SERVER + "users/", user, UserApp.class)).thenReturn(tb);

		clidel.save(user);
		
		UserApp y= clidel.findById(0);
		
		
		assertTrue(y.equals(user));
		
		
	}
	@Test
	public void testBusFindAll(){
		UserApp user=new UserApp();
		user.setId(0);
		user.setPassword("123");
		user.setType(UserType.admin);
		user.setUsername("Santiago");
		
		UserApp user1=new UserApp();
		user1.setId(1);
		user1.setPassword("123");
		user1.setType(UserType.operador);
		user1.setUsername("jose");
		
	ResponseEntity<UserApp> tb = new ResponseEntity<UserApp>(user, HttpStatus.ACCEPTED);
		
		when(rest.postForEntity(clidel.SERVER + "users", user, UserApp.class)).thenReturn(tb);

		clidel.save(user);
		
		clidel.save(user1);
		
		ArrayList<UserApp> y=(ArrayList<UserApp>) clidel.findAll();
		
		assertTrue(	y.contains(user)&&y.contains(user1));
		
		
	}
	
	@Test
	public void testBusFindAllAdmins(){
		UserApp user=new UserApp();
		user.setId(0);
		user.setPassword("123");
		user.setType(UserType.admin);
		user.setUsername("Santiago");
		
		UserApp user1=new UserApp();
		user1.setId(1);
		user1.setPassword("123");
		user1.setType(UserType.operador);
		user1.setUsername("jose");
		
		ResponseEntity<UserApp> tb = new ResponseEntity<UserApp>(user, HttpStatus.ACCEPTED);
		
		when(rest.postForEntity(clidel.SERVER + "admins", user, UserApp.class)).thenReturn(tb);

		clidel.save(user);
		
		clidel.save(user1);
		
		ArrayList<UserApp> y=(ArrayList<UserApp>) clidel.findAllAdmins();
		
		assertTrue(	y.contains(user));

		
	}
	
	@Test
	public void testBusFindAlloper(){
		UserApp user=new UserApp();
		user.setId(0);
		user.setPassword("123");
		user.setType(UserType.admin);
		user.setUsername("Santiago");
		
		UserApp user1=new UserApp();
		user1.setId(1);
		user1.setPassword("123");
		user1.setType(UserType.operador);
		user1.setUsername("jose");
		
		ResponseEntity<UserApp> tb = new ResponseEntity<UserApp>(user, HttpStatus.ACCEPTED);
		
		when(rest.postForEntity(clidel.SERVER + "operators", user, UserApp.class)).thenReturn(tb);

		clidel.save(user);
		
		clidel.save(user1);
		
		ArrayList<UserApp> y=(ArrayList<UserApp>) clidel.findAllAdmins();
		
		assertTrue(	y.contains(user1));		

		
	}
	
	
}
