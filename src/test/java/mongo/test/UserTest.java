package mongo.test;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import server.rssreader.entity.User;
import server.rssreader.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/beans.xml")
public class UserTest {
	
	@Autowired
	UserRepository userRepository;

	@Test
	public void testAdd() throws Exception {
		User u = new User();
		u.setUsername("admin");
		u.setPassword("admin");
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] result = md.digest(u.getPassword().getBytes());
		
		System.out.println(Hex.encodeHexString(result));
		
		String pwd = Hex.encodeHexString(result) + u.getUsername() + "8877";
		result = md.digest(pwd.getBytes());
		String finalPwd = Hex.encodeHexString(result);
		System.out.println(finalPwd);
		
		u.setPassword(finalPwd);
		
		userRepository.add(u);
	}
	
	@Test
	public void testAuth() {
		String username = "admin";
		String password = "de128adee6d5143bcf8c463e93b6496b";
		
		boolean authResult = userRepository.auth(username, password);
		System.out.println(authResult);
	}
}
