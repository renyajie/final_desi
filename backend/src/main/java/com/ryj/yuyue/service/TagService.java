package com.ryj.yuyue.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassTag;
import com.ryj.yuyue.bean.ClassTagResult;
import com.ryj.yuyue.bean.UserFeature;
import com.ryj.yuyue.dao.ClassTagMapper;

/**
 * 处理和课程标签相关的功能
 * @author Thor
 *
 */
@Service
public class TagService {

	@Autowired
	private ClassTagMapper classTagMapper;
	
	//表示第一类标签的取值
	private Integer[][] tagOneValue = {
			{1, 0, 0}, {0, 1, 0}, {0, 0, 1}
	};
	
	//代表第二类标签的取值
	private Integer[][] tagTwoValue = {
			{1, 0}, {0, 1}
	};
	
	/**
	 * 添加课程种类标签
	 * @param tagOne 第一类标签：轻松，强烈，普通，取值为1,2,3，表示选中哪一个
	 * @param tagTwo 第二类标签：恢复，健体，取值为1,2，表示选中哪一个
	 * @param tagThree 第三类标签：调理，取值为1或0，表示选中或不选中
	 * @param tagFour 第四类标签：消耗，取值为1或0，表示选中或不选中
	 * @param classKId 课程种类编号
	 */
	public void addClassTag(
			Integer tagOne, Integer tagTwo, 
			Integer tagThree, Integer tagFour, Integer classKId) {
		
		ClassTag classTag = new ClassTag();
		classTag.setClassKId(classKId);
		classTag.setRelaxed(tagOneValue[tagOne - 1][0]);
		classTag.setIntense(tagOneValue[tagOne - 1][1]);
		classTag.setCommon(tagOneValue[tagOne - 1][2]);
		classTag.setRecovery(tagTwoValue[tagTwo - 1][0]);
		classTag.setEnhance(tagTwoValue[tagTwo - 1][1]);
		classTag.setNurse(tagThree);
		classTag.setConsume(tagFour);
		
		classTagMapper.insertSelective(classTag);
	}
	
	/**
	 * 更新课程标签
	 * @param tagOne
	 * @param tagTwo
	 * @param tagThree
	 * @param tagFour
	 * @param id
	 */
	public void updateClassTag(
			Integer tagOne, Integer tagTwo, 
			Integer tagThree, Integer tagFour, Integer id) {
		
		ClassTag classTag = classTagMapper.selectByPrimaryKey(id);
		
		classTag.setRelaxed(tagOneValue[tagOne - 1][0]);
		classTag.setIntense(tagOneValue[tagOne - 1][1]);
		classTag.setCommon(tagOneValue[tagOne - 1][2]);
		classTag.setRecovery(tagTwoValue[tagTwo - 1][0]);
		classTag.setEnhance(tagTwoValue[tagTwo - 1][1]);
		classTag.setNurse(tagThree);
		classTag.setConsume(tagFour);
		
		classTagMapper.updateByPrimaryKeySelective(classTag);
	}
	
	/**
	 * 获取课程标签
	 * @param placeId
	 * @param classKId
	 */
	public List<ClassTagResult> getClassTag(
			Integer placeId, Integer classKId) {
		return classTagMapper.getClassTag(placeId, classKId);
	}
	
	/**
	 * 根据用户偏好获得推荐课程种类的编号
	 * @param userFeature
	 * @param property
	 * @return
	 */
	public List<Integer> getRecommandIdFromUserFeature(
			UserFeature userFeature, String property) {
		Random rand = new Random();
		
		int relaxed, intense, common, recovery, enhance, nurse, consume;
		
		//设置第一类属性
		relaxed = userFeature.getIllnese();
		if(relaxed == 0) {
			intense = rand.nextInt(2);
			common = intense == 1 ? 0 : 1;
		}
		else {
			intense = 0;
			common = 0;
		}
		
		//设置第二类属性
		recovery = userFeature.getSurgery();
		enhance = recovery == 1 ? 0 : 1;
		
		//设置第三类属性
		nurse = userFeature.getBalanceDiet();
		
		//设置第四类属性
		consume = userFeature.getLimitIntake() == 1 ? 0 : 1;
		
		return classTagMapper.getRecommandIdFromFeature(
				relaxed, intense, common, recovery, 
				enhance, nurse, consume, property);
	}
	
	/**
	 * 获取一个课程标签
	 * @param id
	 * @return
	 */
	public ClassTagResult getOneClassTag(
			Integer id) {
		return classTagMapper.getOneClassTag(id);
	}
}
