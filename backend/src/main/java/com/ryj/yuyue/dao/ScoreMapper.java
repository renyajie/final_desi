package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.bean.ScoreExample;
import com.ryj.yuyue.bean.ScoreResult;

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
     * 查询评价
     * @param classKId 课程种类编号
     * @param userId 用户编号
     * @return
     */
    List<ScoreResult> getScore(
    		@Param("classKId") Integer classKId,
    		@Param("userId") Integer userId);
}