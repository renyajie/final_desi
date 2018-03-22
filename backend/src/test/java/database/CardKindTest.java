package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.CardKindMapper;

/**
 * 和会员卡种类有关的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CardKindTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(CardKindTest.class);
	
	@Autowired
	private CardKindMapper cardKindMapper;
	
	@Test
	public void getCardKindTest() throws Exception {
		logger.info("getCardKindTest: {}", 
				cardKindMapper.getCardKind(
						1,
						"黄",
						null,
						null));
	}
}
