package mongo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import server.rssreader.entity.FetchUrl;
import server.rssreader.repository.FetchUrlRepository;
import server.rssreader.util.Pagination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/beans.xml")
public class FetchUrlTest {
	
	@Autowired
	FetchUrlRepository fetchUrlRepository;

	@Test
	public void testAdd() {
		FetchUrl fu = new FetchUrl();
		fu.setSourceName("博客园");
		fu.setFetchUrl("http://feed.cnblogs.com/blog/sitehome/rss");
		
		this.fetchUrlRepository.save(fu);
	}

	@Test
	public void testQuery() {
		FetchUrl fu = this.fetchUrlRepository.get("533970af6433753afc18d90c");
		
		System.out.println(fu);
	}
	
	@Test
	public void testUpdate() {
		FetchUrl fu = this.fetchUrlRepository.get("533970af6433753afc18d90c");
		
		fu.setSourceName("cnblog");
		
		this.fetchUrlRepository.update(fu);
	}
	
	@Test
	public void testPage() {
		Pagination pageInfo = Pagination.newInstance();
		pageInfo.setPageIndex(1);
		pageInfo.setPageSize(10);
		
		List<FetchUrl> list = (List<FetchUrl>)this.fetchUrlRepository.getByPage(pageInfo, "source_name", "CSDN");
		
		for(FetchUrl url : list) {
			System.out.println(url);
		}
	}
	
	@Test
	public void testDelete() {
		this.fetchUrlRepository.delete("533970426433264d8fab81ca");
	}
}
