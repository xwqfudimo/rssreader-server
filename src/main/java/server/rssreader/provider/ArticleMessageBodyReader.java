package server.rssreader.provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import server.rssreader.entity.Article;
import server.rssreader.util.JsonUtil;

@Consumes(MediaType.APPLICATION_JSON)
@Provider
public class ArticleMessageBodyReader implements MessageBodyReader<Article> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type == Article.class;
	}

	@Override
	public Article readFrom(Class<Article> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		Article article = (Article)JsonUtil.readFromJson(entityStream, Article.class);
		
		return article;
	}

}
