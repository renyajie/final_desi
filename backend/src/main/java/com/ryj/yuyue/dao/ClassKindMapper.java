package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassKindExample;
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
}