package server.rssreader.repository;

import server.rssreader.entity.User;

public interface UserRepository {
	boolean auth(String username, String password);
	void add(User user);
}
