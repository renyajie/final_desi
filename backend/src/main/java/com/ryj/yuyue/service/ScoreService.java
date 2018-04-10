package com.ryj.yuyue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.bean.ScoreResult;
import com.ryj.yuyue.dao.ClassInfoMapper;
import com.ryj.yuyue.dao.ScoreMapper;

/**
 * 和用户评分相关的功能
 * 
 * 1. 添加用户评分
 * 2. 查询评分
 * @author Thor
 *
 */
@Service
public class ScoreService {
	
	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	/**
	 * 插入评分数据
	 * @param score
	 */
	public void insertScore(Score score) {
		
		//加入课程种类编号
		int classKId = classInfoMapper
				.selectByPrimaryKey(score.getClaId())
				.getClaKId();
		score.setClaKId(classKId);
		
		//生成评价时间
		score.setScoreTime(new Date());
		
		scoreMapper.insertSelective(score);
	}
	
	/**
	 * 获取用户评价
	 * @param classKId 课程种类编号
	 * @param userId 用户编号
	 * @return
	 */
	public List<ScoreResult> getScore(Integer classKId, Integer userId) {
		return scoreMapper.getScore(classKId, userId);
	}
}
