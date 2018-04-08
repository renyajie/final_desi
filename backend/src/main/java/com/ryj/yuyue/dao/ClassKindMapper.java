package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassKindExample;
import com.ryj.yuyue.bean.ClassKindResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassKindMapper {
    long countByExample(ClassKindExample example);

    int deleteByExample(ClassKindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassKind record);

    int insertSelective(ClassKind record);

    List<ClassKind> selectByExample(ClassKindExample example);

    ClassKind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassKind record, @Param("example") ClassKindExample example);

    int updateByExample(@Param("record") ClassKind record, @Param("example") ClassKindExample example);

    int updateByPrimaryKeySelective(ClassKind record);

    int updateByPrimaryKey(ClassKind record);
    
    /**
     * 管理员获取课程种类信息
     * @param placeId
     * @param classKId
     * @param managerId
     * @param kName
     * @param property
     * @param difficulty
     * @return
     */
    List<ClassKindResult> getClassKind(
    		@Param("placeId") Integer placeId,
    		@Param("classKId") Integer classKId,
    		@Param("managerId") Integer managerId, 
    		@Param("kName") String kName, 
    		@Param("property") String property,
    		@Param("difficulty") Integer difficulty);
    
    /**
     * 获取编号列表中所有编号的课程种类
     * @param idList 编号列表
     * @return
     */
    List<ClassKindResult> getClassKindByIdList(
    		@Param("idList") List<Integer> idList);
}