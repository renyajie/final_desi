package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassTag;
import com.ryj.yuyue.bean.ClassTagExample;
import com.ryj.yuyue.bean.ClassTagResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassTagMapper {
    long countByExample(ClassTagExample example);

    int deleteByExample(ClassTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassTag record);

    int insertSelective(ClassTag record);

    List<ClassTag> selectByExample(ClassTagExample example);

    ClassTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassTag record, @Param("example") ClassTagExample example);

    int updateByExample(@Param("record") ClassTag record, @Param("example") ClassTagExample example);

    int updateByPrimaryKeySelective(ClassTag record);

    int updateByPrimaryKey(ClassTag record);
    
    /**
     * 根据场馆和课程种类编号，获取课程标签
     * @param placeId
     * @param classKId
     * @return
     */
    List<ClassTagResult> getClassTag(
    		@Param("placeId") Integer placeId, 
    		@Param("classKId") Integer classKId);
    
    /**
     * 获取一个课程编号
     * @param id
     * @return
     */
    ClassTagResult getOneClassTag(
    		@Param("id") Integer id);
}