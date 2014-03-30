package mongo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import server.rssreader.dto.ArticleDto;
import server.rssreader.repository.ArticleRepository;
import server.rssreader.resource.ArticleResource;
import server.rssreader.util.Pagination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/beans.xml")
public class SearchTest {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleResource articleResource;
	
	@Test
	public void test() {
		Pagination pageInfo = Pagination.newInstance();
		pageInfo.setPageIndex(1);
		pageInfo.setPageSize(15);
		
		String title = "";
		
		List<ArticleDto> list = this.articleRepository.getByPage(pageInfo, title);
		
		System.out.println(list.size());
		
		for(ArticleDto dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void test2() {
		String result = this.articleResource.getByPage(1, 10, "");
		
		System.out.println(result);
	}
}
