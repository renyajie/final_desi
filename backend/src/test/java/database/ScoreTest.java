package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.ScoreMapper;

/**
 * 课程信息的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ScoreTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(ScoreTest.class);
	
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Test
	public void getScoreTest() throws Exception {
		logger.info("getScoreTest: {}", 
				scoreMapper.getScore(null, null, null));
	}
	
	@Test
	public void recommandForNewUserTest() {
		logger.info("recommandForNewUserTest: {}", 
				scoreMapper.recommandForNewUser(25, "男", "g"));
	}
	
	@Test
	public void setTest() {
		
		List<Integer> idList = new ArrayList<Integer>(Arrays.asList(1, 3));
		Set<Integer> idSet = new HashSet<Integer>(idList);
		idSet.add(2);
		System.out.println(idSet);
	}
	
	@Test
	public void getScoreNumber() {
		System.out.println(scoreMapper.getScoreNumber(1, "g"));
	}
}
