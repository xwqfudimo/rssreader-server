package server.rssreader.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import server.rssreader.repository.BaseRepository;
import server.rssreader.util.Pagination;

@Repository
public class BaseRepositoryImpl<T> implements BaseRepository<T> {
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	private Class<T> clz;
	
	@SuppressWarnings("unchecked")
	protected Class<T> getClz() {
		if(clz == null) { 
			clz = (Class<T>)((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
		}
		return clz;
	}
	
	@Override
	public void save(T t) {
		mongoTemplate.insert(t);
	}

	@Override
	public void delete(T t) {
		mongoTemplate.remove(t);
	}

	@Override
	public void update(T t) {
		mongoTemplate.save(t);
	}

	@Override
	public T get(String id) {
		return mongoTemplate.findById(id, getClz());
	}
	
	@Override
	public void update(String json, Update update, boolean first) {
		BasicQuery query = new BasicQuery(json);
		if(first) {
			mongoTemplate.updateFirst(query, update, getClz());
		} else {
			mongoTemplate.updateMulti(query, update, getClz());
		}
	}
	
	@Override
	public List<T> getByPage(Pagination pageInfo, Object...params) {
		Query query = initPageQuery(pageInfo);
		
		addQueryParams(query, params);
		
		return mongoTemplate.find(query, getClz());
	}
	
	protected Query initPageQuery(Pagination pageInfo) {
		Query query = new Query();
		query.skip((pageInfo.getPageIndex()-1) * pageInfo.getPageSize());
		query.limit(pageInfo.getPageSize());
		return query;
	}

	@Override
	public List<T> query(String json, Object... params) {
		BasicQuery query = new BasicQuery(json);
		return mongoTemplate.find(query, getClz());
	}

	@Override
	public void delete(String id) {
		T t = null;
		try {
			t = getClz().newInstance();
			BeanUtils.setProperty(t, "id", id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		mongoTemplate.remove(t);
	}

	@Override
	public long getTotalCount(Object...params) {
		if(params.length % 2 != 0) return 0;
		
		Query query = new Query();
		
		addQueryParams(query, params);
		
		long total = mongoTemplate.count(query, getClz());
		return total;
	}
	
	private void addQueryParams(Query query, Object...params) {
		if(params.length >= 2) {
			Criteria c = Criteria.where(params[0].toString()).regex(params[1].toString(), "i");
			for(int i=2; i<params.length; i++) {
				c.and(params[i].toString()).regex(params[i+1].toString(), "i");
			}
			
			query.addCriteria(c);
		}
	}
}
