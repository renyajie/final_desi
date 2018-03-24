package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.NewsMapper;

/**
 * 和新闻有关的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class NewsTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(NewsTest.class);
	
	@Autowired
	private NewsMapper newsMapper;
	
	@Test
	public void getNewsListTest() throws Exception {
		logger.info("getNewsListTest: {}", 
				newsMapper.getNewsList(null, null, null, null, null, null));
	}
}
