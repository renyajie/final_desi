package python;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.ScoreResult;
import com.ryj.yuyue.dao.ScoreMapper;
import com.ryj.yuyue.service.ScoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AddScoreTest {

	@SuppressWarnings("unused")
	private static final Logger logger = 
			LoggerFactory.getLogger(AddScoreTest.class);
	
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Test
	public void addScore() {
		ScoreResult score = scoreMapper.getScoreResultById(1);
		scoreService.addScoreToFile(score, 1);
	}
}
