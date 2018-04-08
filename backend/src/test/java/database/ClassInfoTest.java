package database;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.ClassInfoMapper;

/**
 * 课程信息的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ClassInfoTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(ClassInfoTest.class);
	
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	@Test
	public void getClassInfoTest() throws Exception {
		logger.info("getClassInfoTest: {}", 
				classInfoMapper.getClassInfo(
						null, null, null, null, null, null, null, null));
	}
	
	@Test
	public void getOneClassInfoTest() throws Exception {
		logger.info("getOneClassInfoTest: {}", 
				classInfoMapper.getOneClassInfo(1));
	}
	
	@Test
	public void getClassInfoByIdTest() throws Exception {
		logger.info("getClassInfoByIdTest: {}", 
				classInfoMapper.getClassInfoByIdList(Arrays.asList(1, 2, 3, 4, 5)));
	}
}
