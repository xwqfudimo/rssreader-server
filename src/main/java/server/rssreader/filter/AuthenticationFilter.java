package server.rssreader.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import server.rssreader.repository.UserRepository;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		if(!requestContext.getUriInfo().getPath().startsWith("/auth")) {
			String username = requestContext.getHeaderString("username");
			String password = requestContext.getHeaderString("password");
			
			boolean authResult = this.userRepository.auth(username, password);
			
			if(!authResult) {
				requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
						.entity("user authentication failed!").build());
			}
		}
	}
}
