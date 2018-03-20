package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.CardOrder;
import com.ryj.yuyue.bean.CardOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardOrderMapper {
    long countByExample(CardOrderExample example);

    int deleteByExample(CardOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardOrder record);

    int insertSelective(CardOrder record);

    List<CardOrder> selectByExample(CardOrderExample example);

    CardOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardOrder record, @Param("example") CardOrderExample example);

    int updateByExample(@Param("record") CardOrder record, @Param("example") CardOrderExample example);

    int updateByPrimaryKeySelective(CardOrder record);

    int updateByPrimaryKey(CardOrder record);
}