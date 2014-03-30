package server.rssreader.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Update;

import server.rssreader.util.Pagination;

public interface BaseRepository<T> {
	void save(T t);

	void delete(T t);

	void delete(String id);

	void update(T t);

	T get(String id);

	List getByPage(Pagination pageInfo, Object... params);

	List<T> query(String json, Object... params);

	void update(String json, Update update, boolean first);

	long getTotalCount(Object...params);
}
