package server.rssreader.repository.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import server.rssreader.dto.ArticleDto;
import server.rssreader.entity.Article;
import server.rssreader.repository.ArticleRepository;
import server.rssreader.util.Pagination;

@Repository
public class ArticleRepositoryImpl extends BaseRepositoryImpl<Article>
		implements ArticleRepository {

	@Override
	public List getByPage(Pagination pageInfo, Object... params) {
		String title = String.valueOf(params[0]);
		
		Query query = initPageQuery(pageInfo);
		
		Criteria c = Criteria.where("title").regex(title, "i");
		query.addCriteria(c);
	
		List<ArticleDto> list = this.mongoTemplate.find(query, ArticleDto.class, "article");
		
		return list;
	}

}
