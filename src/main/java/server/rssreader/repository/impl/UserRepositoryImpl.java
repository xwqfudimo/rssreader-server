package server.rssreader.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import server.rssreader.entity.User;
import server.rssreader.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public boolean auth(String username, String password) {
		Query query = new Query();
		
		Criteria criteria = Criteria.where("username").is(username).and("password").is(password);
		query.addCriteria(criteria);
		
		long count = mongoTemplate.count(query, User.class);
		
		if(count > 0) return true;
		return false;
	}

	@Override
	public void add(User user) {
		mongoTemplate.insert(user);
	}

}
