package server.rssreader.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Article {
	@Id
	private String id;
	@Indexed
	private String title;
	private String link;
	@Field("published_date")
	private String publishedDate;
	private String content;
	private String sourceName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", link=" + link
				+ ", publishedDate=" + publishedDate + ", content=" + content
				+ ", sourceName=" + sourceName + "]";
	}
}
