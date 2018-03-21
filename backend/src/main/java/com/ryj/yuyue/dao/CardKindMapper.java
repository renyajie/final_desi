package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.bean.CardKindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardKindMapper {
    long countByExample(CardKindExample example);

    int deleteByExample(CardKindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardKind record);

    int insertSelective(CardKind record);

    List<CardKind> selectByExample(CardKindExample example);

    CardKind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardKind record, @Param("example") CardKindExample example);

    int updateByExample(@Param("record") CardKind record, @Param("example") CardKindExample example);

    int updateByPrimaryKeySelective(CardKind record);

    int updateByPrimaryKey(CardKind record);
}