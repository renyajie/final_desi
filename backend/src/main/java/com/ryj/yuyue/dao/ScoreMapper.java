package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.bean.ScoreExample;
import com.ryj.yuyue.bean.ScoreResult;
import com.ryj.yuyue.utils.ScoreToken;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapper {
    long countByExample(ScoreExample example);

    int deleteByExample(ScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    List<Score> selectByExample(ScoreExample example);

    Score selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByExample(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    
    /**
     * 获取某种课程的评分
     * @param classKId 课程种类编号
     * @param userId 用户编号
     * @param placeId 场馆编号
     * @return
     */
    List<ScoreResult> getScore(
    		@Param("classKId") Integer classKId,
    		@Param("userId") Integer userId,
    		@Param("placeId") Integer placeId);
    
    /**
     * 谋取指定编号的用户评论数据
     * @param id 评论编号
     * @return
     */
    ScoreResult getScoreResultById(
    		@Param("id") Integer id);
    
    /**
     * 获取所有的用户评分
     * @return
     */
    List<ScoreResult> getAllScore();
    
    /**
     * 为新用户推荐课程
     * @param age 用户年龄
     * @param gender 性别
     * @param property 课程属性
     * @return
     */
    List<ScoreToken> recommandForNewUser(
    		@Param("age") Integer age,
    		@Param("gender") String gender,
    		@Param("property") String property);
}