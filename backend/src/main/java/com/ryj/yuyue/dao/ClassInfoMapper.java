package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassInfoExample;
import com.ryj.yuyue.bean.ClassInfoResult;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassInfoMapper {
    long countByExample(ClassInfoExample example);

    int deleteByExample(ClassInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    List<ClassInfo> selectByExample(ClassInfoExample example);

    ClassInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByExample(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);
    
    List<ClassInfoResult> getClassForUser(
    		@Param("placeId") Integer placeId, 
    		@Param("classDay") Date classDay);
}