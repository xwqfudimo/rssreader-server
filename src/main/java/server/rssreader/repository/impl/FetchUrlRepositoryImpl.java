package server.rssreader.repository.impl;

import org.springframework.stereotype.Repository;

import server.rssreader.entity.FetchUrl;
import server.rssreader.repository.FetchUrlRepository;

@Repository
public class FetchUrlRepositoryImpl extends BaseRepositoryImpl<FetchUrl>
		implements FetchUrlRepository {

}
