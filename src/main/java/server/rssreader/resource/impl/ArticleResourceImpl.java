package server.rssreader.resource.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.rssreader.dto.ArticleDto;
import server.rssreader.entity.Article;
import server.rssreader.repository.ArticleRepository;
import server.rssreader.resource.ArticleResource;
import server.rssreader.util.JsonUtil;
import server.rssreader.util.Pagination;

@Path("/article/")
@Service
public class ArticleResourceImpl implements ArticleResource {
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Response addArticle(Article article, UriInfo uriInfo) {
		this.articleRepository.save(article);
		
		URI createdUri = UriBuilder.fromUri(uriInfo.getRequestUri()).path("{id}").build(article.getId());
		
		Response response = Response.created(createdUri).entity(article).location(createdUri).build();
		
		return response;
	}

	@Override
	public Article getArticle(String id) {
		return this.articleRepository.get(id);
	}

	@Override
	public void updateArticle(Article article) {
		this.articleRepository.update(article);
	}

	@Override
	public void deleteArticle(String id) {
		this.articleRepository.delete(id);
	}

	@Override
	public String getByPage(int pageIndex, int pageSize, String title) {
		Pagination pageInfo = Pagination.newInstance();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		
		List<ArticleDto> list = (List<ArticleDto>)this.articleRepository.getByPage(pageInfo, title);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", this.articleRepository.getTotalCount("title", title));
		
		return JsonUtil.convert(map);
	}

}
