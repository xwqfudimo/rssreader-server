package server.rssreader.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import server.rssreader.entity.Article;
import server.rssreader.util.JsonUtil;

@Produces(MediaType.APPLICATION_JSON)
@Provider
public class ArticleMessageBodyWriter implements MessageBodyWriter<Article> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type == Article.class;
	}

	@Override
	public long getSize(Article t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Article t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		JsonUtil.writeToJson(entityStream, t);
	}

}
