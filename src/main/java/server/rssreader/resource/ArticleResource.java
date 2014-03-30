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

import server.rssreader.entity.Article;


public interface ArticleResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response addArticle(Article article, @Context UriInfo uriInfo);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Article getArticle(@PathParam("id") String id);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	void updateArticle(Article article);
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	void deleteArticle(@PathParam("id") String id);
	
	/*
	@GET
	@Path("/{pageSize}/{pageIndex}")
	@Produces(MediaType.APPLICATION_JSON)
	List<ArticleDto> getByPage(@PathParam("pageIndex") int pageIndex, @PathParam("pageSize") int pageSize);
	*/
	
	@GET
	@Path("/{pageSize}/{pageIndex}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByPage(@PathParam("pageIndex") int pageIndex, @PathParam("pageSize") int pageSize, @QueryParam("title") String title);
}
