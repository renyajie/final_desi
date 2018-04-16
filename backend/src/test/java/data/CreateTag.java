package data;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassTag;
import com.ryj.yuyue.dao.ClassKindMapper;
import com.ryj.yuyue.dao.ClassTagMapper;

/**
 * 创建课程标签
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateTag {

	@Autowired
	private ClassKindMapper classKindMapper;
	
	@Autowired
	private ClassTagMapper classTagMapper;
	
	private Integer[] tagOneValue = {1, 2, 3};
	private Integer[] tagTwoValue = {1, 2};
	
	Random rand = new Random();
	
	@Test
	public void addTag() {
		List<ClassKind> classKindList = classKindMapper.selectByExample(null);
		ClassTag tag = null;
		int valueOne, valueTwo;
		
		for(ClassKind classKind: classKindList) {
			tag = new ClassTag();
			
			valueOne = tagOneValue[rand.nextInt(3)];
			if(valueOne == 1) {
				tag.setRelaxed(1);
				tag.setIntense(0);
				tag.setCommon(0);
			}
			else if(valueOne == 2) {
				tag.setRelaxed(0);
				tag.setIntense(1);
				tag.setCommon(0);
			}
			else {
				tag.setRelaxed(0);
				tag.setIntense(0);
				tag.setCommon(1);
			}
			
			valueTwo = tagTwoValue[rand.nextInt(2)];
			if(valueTwo == 1) {
				tag.setRecovery(1);
				tag.setEnhance(0);
			}
			else {
				tag.setRecovery(0);
				tag.setEnhance(1);
			}
			
			tag.setNurse(rand.nextInt(2));
			tag.setConsume(rand.nextInt(2));
			
			tag.setClassKId(classKind.getId());
			
			classTagMapper.insertSelective(tag);
		}
	}
}
