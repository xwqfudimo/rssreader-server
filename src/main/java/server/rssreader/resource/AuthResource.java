package server.rssreader.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface AuthResource {
	@GET
	@Path("/{username}/{password}")
	boolean auth(@PathParam("username") String username, @PathParam("password") String password);
}
