package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.CardOrderMapper;
import com.ryj.yuyue.utils.DateMethod;

/**
 * 和购卡有关的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CardOrderTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(CardOrderTest.class);
	
	@Autowired
	private CardOrderMapper cardOrderMapper;
	
	@Test
	public void getCardOrderTest() throws Exception {
		logger.info("getClassInfoTest: {}", 
				cardOrderMapper.getCardOrder(
						1,
						null,
						null,
						DateMethod.getDateFromString("2018-3-2a5")));
	}
}
