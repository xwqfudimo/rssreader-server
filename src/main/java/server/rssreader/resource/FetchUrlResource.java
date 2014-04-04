package server.rssreader.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import server.rssreader.entity.FetchUrl;

public interface FetchUrlResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response addFetchUrl(FetchUrl fetchUrl, @Context UriInfo uriInfo);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	FetchUrl getFetchUrl(@PathParam("id") String id);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	void updateFetchUrl(FetchUrl fetchUrl);
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	void deleteFetchUrl(@PathParam("id") String id);
	
	@GET
	@Path("/{pageSize}/{pageIndex}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByPage(@PathParam("pageIndex") int pageIndex, @PathParam("pageSize") int pageSize, @QueryParam("sourceName") String sourceName);
}
