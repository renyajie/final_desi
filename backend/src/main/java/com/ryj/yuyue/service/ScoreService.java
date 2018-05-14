package com.ryj.yuyue.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassInfoResult;
import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.bean.ScoreResult;
import com.ryj.yuyue.dao.ClassInfoMapper;
import com.ryj.yuyue.dao.ScoreMapper;
import com.ryj.yuyue.utils.ConstantLiteral;
import com.ryj.yuyue.utils.DateMethod;

/**
 * 和用户评分相关的功能
 * 
 * 1. 添加用户评分 2. 查询评分
 * 
 * @author Thor
 *
 */
@Service
public class ScoreService {

	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	private ClassInfoMapper classInfoMapper;

	public static final int PEOPLE = 1;
	public static final int INDIVIDUAL = 0;

	/**
	 * 向文件中插入评分数据
	 * 
	 * @param score
	 */
	public void insertScore(Score score) {

		ClassInfoResult classInfo = classInfoMapper
				.getClassInfo(score.getClaId(), null, null, null, null, null, null, null).get(0);

		// 加入课程种类编号
		score.setClaKId(classInfo.getClaKId());

		// 生成评价时间
		score.setScoreTime(new Date());

		scoreMapper.insertSelective(score);

		if(score.getId() == null) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
		addScoreToFile(scoreMapper.getScoreResultById(score.getId()),
				classInfo.getProperty().equals("s") ? INDIVIDUAL : PEOPLE);
	}

	/**
	 * 获取用户评价
	 * 
	 * @param classKId
	 *            课程种类编号
	 * @param userId
	 *            用户编号
	 * @return
	 */
	public List<ScoreResult> getScore(
			Integer classKId, Integer userId, Integer placeId) {
		return DateMethod.fixScoreResultTime(
				scoreMapper.getScore(classKId, userId, placeId));
	}

	/**
	 * 向文件中写入用户评论数据
	 * 
	 * @param score
	 *            用户评论数据
	 * @param isPeople
	 *            是否为团课评论
	 */
	@SuppressWarnings("resource")
	public void addScoreToFile(ScoreResult score, Integer isPeople) {

			FileWriter fw = null;
			if (isPeople == PEOPLE) {
				try {
					fw = new FileWriter(ConstantLiteral.PEOPLE_SCORE, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					fw = new FileWriter(ConstantLiteral.INDIVIDUAL_SCORE, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			BufferedWriter bw = new BufferedWriter(fw);
			try {
				bw.append(score.getuId() + ", ");
				bw.append(score.getuName() + ", ");
				bw.append(score.getClaKId() + ", ");
				bw.append(score.getClassKName() + ", ");
				bw.append(score.getScore() + "\n");
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
