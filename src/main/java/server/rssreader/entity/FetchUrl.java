package server.rssreader.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="fetch_url")
public class FetchUrl {
	@Id
	private String id;
	@Field("fetch_url")
	private String fetchUrl;
	@Field("source_name")
	private String sourceName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFetchUrl() {
		return fetchUrl;
	}
	public void setFetchUrl(String fetchUrl) {
		this.fetchUrl = fetchUrl;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	@Override
	public String toString() {
		return "FetchUrl [id=" + id + ", fetchUrl=" + fetchUrl
				+ ", sourceName=" + sourceName + "]";
	}
}
