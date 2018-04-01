package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.ClassKindMapper;

/**
 * 课程种类的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ClassKindTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(ClassKindTest.class);
	
	@Autowired
	private ClassKindMapper classKindMapper;
	
	@Test
	public void getClassKindTest() {
		logger.info("getClassKindTest: {}", 
				classKindMapper.getClassKind(2, null, null, null, null, null));
	}

}
