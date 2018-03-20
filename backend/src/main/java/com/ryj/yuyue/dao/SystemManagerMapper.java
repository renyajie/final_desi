package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.SystemManager;
import com.ryj.yuyue.bean.SystemManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemManagerMapper {
    long countByExample(SystemManagerExample example);

    int deleteByExample(SystemManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemManager record);

    int insertSelective(SystemManager record);

    List<SystemManager> selectByExample(SystemManagerExample example);

    SystemManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemManager record, @Param("example") SystemManagerExample example);

    int updateByExample(@Param("record") SystemManager record, @Param("example") SystemManagerExample example);

    int updateByPrimaryKeySelective(SystemManager record);

    int updateByPrimaryKey(SystemManager record);
}