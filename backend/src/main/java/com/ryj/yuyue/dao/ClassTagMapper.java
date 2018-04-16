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
    
    /**
     * 根据用户偏好获得推荐课程种类的编号
     * @param relaxed
     * @param intense
     * @param common
     * @param recovery
     * @param enhance
     * @param nurse
     * @param consume
     * @param property
     * @return
     */
    List<Integer> getRecommandIdFromFeature(
    		@Param("relaxed") Integer relaxed,
    		@Param("intense") Integer intense,
    		@Param("common") Integer common,
    		@Param("recovery") Integer recovery,
    		@Param("enhance") Integer enhance,
    		@Param("nurse") Integer nurse,
    		@Param("consume") Integer consume,
    		@Param("property") String property);
    
}