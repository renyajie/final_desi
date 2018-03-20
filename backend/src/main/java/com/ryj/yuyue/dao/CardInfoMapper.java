package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.CardInfo;
import com.ryj.yuyue.bean.CardInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardInfoMapper {
    long countByExample(CardInfoExample example);

    int deleteByExample(CardInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    int insertSelective(CardInfo record);

    List<CardInfo> selectByExample(CardInfoExample example);

    CardInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardInfo record, @Param("example") CardInfoExample example);

    int updateByExample(@Param("record") CardInfo record, @Param("example") CardInfoExample example);

    int updateByPrimaryKeySelective(CardInfo record);

    int updateByPrimaryKey(CardInfo record);
}