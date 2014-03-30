package mongo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import server.rssreader.entity.Article;
import server.rssreader.repository.ArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/beans.xml")
public class MongoTest {
	
	@Autowired
	ArticleRepository articleRepository;

	@Test
	public void testSave() {
		Article t = new Article();
		t.setLink("http://www.kevinxwq.com");
		t.setContent("this is a test");
		t.setTitle("test");
		
		articleRepository.save(t);
	}

	@Test
	public void testQuery() {
		Article a = articleRepository.get("532d38a285c14ee95b88f6b7");
		System.out.println(a);
	}
	
	@Test
	public void testUpdate() {
		Article t = new Article();
		t.setId("532d38a285c14ee95b88f6b7");
		t.setLink("http://www.google.com");
		t.setContent("google is good");
		t.setTitle("google");
		
		articleRepository.update(t);
	}
	
	@Test
	public void testDelete() {
		Article t = new Article();
		t.setId("532d38a285c14ee95b88f6b7");
		
		articleRepository.delete(t);
	}
}
