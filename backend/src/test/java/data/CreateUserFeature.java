package data;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.bean.UserFeature;
import com.ryj.yuyue.dao.UserFeatureMapper;
import com.ryj.yuyue.dao.UserMapper;

/**
 * 创建用户特征数据
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateUserFeature {

	@Autowired
	private UserFeatureMapper userFeatureMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	private Random random;
	
	public List<User> getUser() {
		return userMapper.selectByExample(null);
	}
	
	@Test
	public void addUserFeature() {
		List<User> userList = getUser();
		UserFeature userFeature = null;
		random = new Random();
		for(User user: userList) {
			userFeature = new UserFeature();
			userFeature.setBalanceDiet(random.nextInt(2));
			userFeature.setIllnese(random.nextInt(2));
			userFeature.setLimitIntake(random.nextInt(2));
			userFeature.setSurgery(random.nextInt(2));
			userFeature.setuId(user.getId());
			userFeatureMapper.insertSelective(userFeature);
		}
	}
}
