package mongo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import server.rssreader.dto.ArticleDto;
import server.rssreader.repository.ArticleRepository;
import server.rssreader.util.Pagination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/beans.xml")
public class PageQueryTest {
	
	@Autowired
	ArticleRepository articleRepository;

	@Test
	public void test() {
		Pagination pageInfo = Pagination.newInstance();
		pageInfo.setPageIndex(1);
		pageInfo.setPageSize(3);
		
		List<ArticleDto> list = (List<ArticleDto>)this.articleRepository.getByPage(pageInfo);
		
		for(ArticleDto dto : list) {
			System.out.println(dto);
		}
	}

}
