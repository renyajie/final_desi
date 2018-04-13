package com.ryj.yuyue.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.bean.ScoreExample;
import com.ryj.yuyue.bean.ScoreExample.Criteria;
import com.ryj.yuyue.controller.HomeController;
import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.dao.ClassKindMapper;
import com.ryj.yuyue.dao.ScoreMapper;
import com.ryj.yuyue.dao.UserMapper;
import com.ryj.yuyue.utils.CallPython;
import com.ryj.yuyue.utils.ScoreToken;

/**
 * 和推荐算法有关的功能
 * 1. 获取团课推荐列表
 * 2. 获取私教推荐列表
 * 3. 保存推荐记录(用于推荐算法)
 * @author Thor
 *
 */
@Service
public class RecommandService {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommandService.class);
	
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Autowired
	private ClassKindMapper classKindMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public static final int RECOMMEND_SIZE = 5;
	
	/**
	 * 获取某个用户的课程推荐结果
	 * 1. 若用户没有评论记录
	 * 	1.1.若没有其他用户:
	 * 		根据专业知识进行推荐
	 * 	1.2.若有其他用户:
	 * 		根据当前评分记录中和用户性别与年龄相似的用户进行推荐
	 * 2. 若有预约记录
	 *  1.1.若没有其他用户:
	 * 		根据专业知识进行推荐
	 * 	1.2.若有其他用户:
	 * 		1.2.1 若其他用户没有评论:
	 * 			根据专业知识进行推荐
	 * 		1.2.2 若其他用户有评论:
	 * 			使用python推荐算法进行推荐
	 * @param userId 用户编号
	 * @param isPeople 是否为团课，若为团课则isPeople为1，否则为0
	 * @return
	 */
	public List<ClassKindResult> getRecommandResult(Integer userId, Integer isPeople) {
		List<Integer> idList = new ArrayList<Integer>();
		User user = userMapper.selectByPrimaryKey(userId);
		
		ScoreExample exampleOne = new ScoreExample();
		Criteria criteriaOne = exampleOne.createCriteria();
		criteriaOne.andUIdEqualTo(userId);
		
		ScoreExample exampleTwo = new ScoreExample();
		Criteria criteriaTwo = exampleTwo.createCriteria();
		criteriaTwo.andUIdNotEqualTo(userId);
		
		//当前用户的评论数量
		int scoreSize = (int) scoreMapper.countByExample(exampleOne);
		//其他用户的评论数量
		int otherScoreSize = (int) scoreMapper.countByExample(exampleTwo);
		//当前系统的用户量
		int userSize = (int) userMapper.countByExample(null);
		
		//若用户没有评论记录，则使用统计推荐
		if(scoreSize == 0) {
			
			//若是第一个用户，则根据专业知识进行推荐
			if(userSize == 0) {
				idList = getStaticRecommand(
						isPeople, RECOMMEND_SIZE, new ArrayList<Integer>());
				logger.info("用户没有评论，而是第一个用户，使用专业知识进行推荐");
			}
			//若不是第一个用户，则统计和用户人口特征相似的用户热门课程推荐
			else {
				List<ScoreToken> hotList = 
						scoreMapper.recommandForNewUser(
								user.getAge(), user.getGender(), "g");
				idList.clear();
				
				//获取指定数量的推荐项目
				for(int i = 0; i < RECOMMEND_SIZE; i++) {
					idList.add(hotList.get(i).getClassKindId());
				}
				logger.info("用户没有评论，而不是第一个用户，使用统计相似用户热门课程推荐");
			}
			
		}
		//若有评论
		else {
			
			//若是第一个用户，则根据专业知识进行推荐
			if(userSize == 0) {
				idList = getStaticRecommand(
						isPeople, RECOMMEND_SIZE, new ArrayList<Integer>());
				logger.info("用户有评论，是第一个用户，专业知识推荐");
			}
			else {
				//若其他用户没有评论信息，根据专业知识进行静态推荐
				if(otherScoreSize == 0) {
					idList = getStaticRecommand(
							isPeople, RECOMMEND_SIZE, new ArrayList<Integer>());
					logger.info("用户有评论，其他用户没评论，专业知识推荐");
				}
				//若其他用户有评论
				else {
					//根据课程属性，调用相应的python算法，获取推荐结果
					List<Integer> temp = new ArrayList<Integer>();
					if(isPeople == 1) {
						temp = CallPython.getPeopleClassRecommand(userId);
					}
					else {
						temp = CallPython.getIndividualClassRecommand(userId);
					}
					
					//若推荐结果不足指定个数，则再根据专业知识进行补充
					if(temp.size() < 5) {
						idList = getStaticRecommand(
								isPeople, RECOMMEND_SIZE - temp.size(), temp);
					}
					else {
						idList = temp;
					}
					logger.info("用户有评论，其他用户有评论，使用python推荐算法进行推荐");
				}
			}
			
		}
		
		return classKindMapper.getClassKindByIdList(idList);
	}
	
	
	/**
	 * 获取根据专业知识的静态推荐
	 * @param isPeople 是否团课
	 * @param size 推荐多少个
	 * @param temp 已有的推荐课程编号列表
	 * @return
	 */
	public List<Integer> getStaticRecommand(
			Integer isPeople, Integer size, List<Integer> temp) {
		
		Random rand = new Random();
		Set<Integer> idSet = new HashSet<Integer>(temp);
		
		if(isPeople == 1) {
			//从团课种类中随机生成五个种类编号
			while(idSet.size() < 5) {
				idSet.add(rand.nextInt(800) + 1);
			}
		}
		else {
			//从私教种类中随机生成五个种类编号
			while(idSet.size() < 5) {
				idSet.add(rand.nextInt(800) + 1 + 800);
			}
		}
		
		return new ArrayList<Integer>(idSet);
	}

}
