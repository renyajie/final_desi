package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.bean.ClassOrderExample;
import com.ryj.yuyue.bean.ClassOrderExample.Criteria;
import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.dao.ClassInfoMapper;
import com.ryj.yuyue.dao.ClassOrderMapper;
import com.ryj.yuyue.dao.ScoreMapper;
import com.ryj.yuyue.dao.UserMapper;

/**
 * 创建用户评价
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateScore {

	@Autowired
	private ScoreMapper scoreMapper;
	
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	Calendar calendar = new GregorianCalendar();
	Date date = new Date();
	
	/**
	 * 获取评论日期
	 * @param date
	 * @return
	 */
	@SuppressWarnings("static-access")
	public Date getScoreDate(Date date) {
		calendar.setTime(date);
	    calendar.add(calendar.DATE, +1);
	    return calendar.getTime();
	}
	
	/**
	 * 获取评论数据
	 * @return
	 * @throws IOException
	 */
	public List<String> getComment() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/comment");
		List<String> result = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(fin);  
		   
	    //Construct BufferedReader from InputStreamReader  
	    BufferedReader br = new BufferedReader(new InputStreamReader(fis));  
	   
	    String line = null;
	    while ((line = br.readLine()) != null) {  
	        result.add(line.trim());
	    }
	   
	    br.close();
	    
	    return result;
	}
	
	/**
	 * 生成用户评价
	 * @throws IOException 
	 */
	//@Test
	public List<Score> getScore() throws IOException {
		
		List<Score> result = new ArrayList<Score>();
		
		
		//获取评论数据
		List<String> commentList = getComment();
		Random rand = new Random();
		int commentSize = commentList.size();
		
		//获取指定区间的用户的课程订单
		ClassOrderExample example = new ClassOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUIdBetween(1, 750);
		List<ClassOrder> classOrderList = 
				classOrderMapper.selectByExample(example);
		
		Score score = null;
		int classKId, classId, placeId, uId;
		ClassInfo classInfo = null;
		Date scoreDate = null;
		User user = null;
		
		float[] scoreList = {
				2.5f, 2.5f, 3.0f, 3.0f, 3.0f, 3.5f, 3.5f, 3.5f, 3.5f, 
				4.0f, 4.0f, 4.0f, 4.0f, 4.5f, 4.5f, 4.5f, 5.0f, 5.0f};
		int scoreSize = scoreList.length;
		
		for(ClassOrder classOrder: classOrderList) {
			
			uId = classOrder.getuId();
			classId = classOrder.getClaId();
			
			classInfo = classInfoMapper.selectByPrimaryKey(classId);
			user = userMapper.selectByPrimaryKey(uId);
			
			placeId = classInfo.getpId();
			classKId= classInfo.getClaKId();
			scoreDate = getScoreDate(classInfo.getcDay());
			
			//创建评价信息
			score = new Score();
			score.setClaId(classId);
			score.setClaKId(classKId);
			score.setComment(commentList.get(rand.nextInt(commentSize)));
			score.setOrderId(classOrder.getId());
			score.setpId(placeId);
			score.setScore(scoreList[rand.nextInt(scoreSize)]);
			score.setuId(uId);
			score.setScoreTime(scoreDate);
			score.setAge(user.getAge());
			score.setGender(user.getGender());
			
			result.add(score);
			//System.out.println(score);
		}
		
		return result;
	}
	
	/**
	 * 添加用户评论
	 * @throws IOException
	 */
	@Test
	public void addScore() throws IOException {
		List<Score> scoreList = getScore();
		for(Score score: scoreList) {
			scoreMapper.insertSelective(score);
		}
	}
}
