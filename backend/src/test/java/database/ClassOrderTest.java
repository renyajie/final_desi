package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.ClassOrderMapper;

/**
 * 课程订单的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ClassOrderTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(ClassOrderTest.class);
	
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	@Test
	public void getClassOrderTest() throws Exception {
		logger.info("getClassOrderTest: {}", 
				classOrderMapper.getClassOrder(
						null,
						null,
						null, 
						null, 
						1, 
						null, 
						null, 
						null));
	}
}
