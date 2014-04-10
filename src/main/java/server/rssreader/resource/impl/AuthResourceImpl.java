package server.rssreader.resource.impl;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.rssreader.repository.UserRepository;
import server.rssreader.resource.AuthResource;

@Path("/auth/")
@Service
public class AuthResourceImpl implements AuthResource {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean auth(String username, String password) {
		return this.userRepository.auth(username, password);
	}

}
