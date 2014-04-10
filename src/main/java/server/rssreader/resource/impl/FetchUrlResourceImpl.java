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

import server.rssreader.entity.FetchUrl;
import server.rssreader.repository.FetchUrlRepository;
import server.rssreader.resource.FetchUrlResource;
import server.rssreader.util.JsonUtil;
import server.rssreader.util.Pagination;

@Path("/fetchurl/")
@Service
public class FetchUrlResourceImpl implements FetchUrlResource {

	@Autowired
	private FetchUrlRepository fetchUrlRepository;
	
	@Override
	public Response addFetchUrl(FetchUrl fetchUrl, UriInfo uriInfo) {
		this.fetchUrlRepository.save(fetchUrl);
		
		URI createdUri = UriBuilder.fromUri(uriInfo.getRequestUri()).path("{id}").build(fetchUrl.getId());
		
		Response response = Response.created(createdUri).entity(fetchUrl).location(createdUri).build();
		
		return response;
	}

	@Override
	public FetchUrl getFetchUrl(String id) {
		return this.fetchUrlRepository.get(id);
	}

	@Override
	public void updateFetchUrl(FetchUrl fetchUrl) {
		this.fetchUrlRepository.update(fetchUrl);
	}

	@Override
	public void deleteFetchUrl(String id) {
		this.fetchUrlRepository.delete(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getByPage(int pageIndex, int pageSize, String sourceName) {
		Pagination pageInfo = Pagination.newInstance();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		
		List<FetchUrl> list = (List<FetchUrl>) this.fetchUrlRepository.getByPage(pageInfo, "source_name", sourceName);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", this.fetchUrlRepository.getTotalCount("source_name", sourceName));
		
		return JsonUtil.convert(map);
	}

}
