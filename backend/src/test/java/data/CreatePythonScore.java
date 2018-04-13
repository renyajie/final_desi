package data;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.ScoreResult;
import com.ryj.yuyue.dao.ClassKindMapper;
import com.ryj.yuyue.dao.ScoreMapper;
import com.ryj.yuyue.service.ScoreService;

/**
 * 创建python评论数据
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreatePythonScore {

	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	private ClassKindMapper classKindMapper;
	
	@Test
	public void addScore() {
		List<ScoreResult> scoreList = scoreMapper.getAllScore();
		
		String property = null;
		
		for(ScoreResult score: scoreList) {
			property = classKindMapper.selectByPrimaryKey(score.getClaKId()).getProperty();
			scoreService.addScoreToFile(
					score, property.equals("s") ? 0 : 1);
		}
	}
}
