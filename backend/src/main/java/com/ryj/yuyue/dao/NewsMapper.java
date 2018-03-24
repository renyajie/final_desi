package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.News;
import com.ryj.yuyue.bean.NewsExample;
import com.ryj.yuyue.bean.NewsResult;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    long countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExampleWithBLOBs(NewsExample example);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);
    
    /**
	 * 查找新闻
	 * @param newsId 新闻编号
	 * @param managerId 管理员编号
	 * @param placeId 场馆编号
	 * @param title 标题
	 * @param before 大于等于此日期
	 * @param after 小于等于此日期
	 * @return
	 */
	List<NewsResult> getNewsList(
			@Param("newsId") Integer newsId,
			@Param("managerId") Integer managerId, 
			@Param("placeId") Integer placeId,
			@Param("title") String title,
			@Param("before") Date before,
			@Param("after") Date after);
}