package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.bean.ClassOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassOrderMapper {
    long countByExample(ClassOrderExample example);

    int deleteByExample(ClassOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassOrder record);

    int insertSelective(ClassOrder record);

    List<ClassOrder> selectByExample(ClassOrderExample example);

    ClassOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassOrder record, @Param("example") ClassOrderExample example);

    int updateByExample(@Param("record") ClassOrder record, @Param("example") ClassOrderExample example);

    int updateByPrimaryKeySelective(ClassOrder record);

    int updateByPrimaryKey(ClassOrder record);
}