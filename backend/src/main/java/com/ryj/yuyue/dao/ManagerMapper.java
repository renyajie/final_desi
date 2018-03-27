package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.Manager;
import com.ryj.yuyue.bean.ManagerExample;
import com.ryj.yuyue.bean.ManagerResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {
    long countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    
    /**
     * 查询管理员列表
     * @param id 编号
     * @param account 账号
     * @param mName 姓名
     * @param sName 瑜伽馆名称
     * @return
     */
    List<ManagerResult> getManagerList(
    		@Param("id") Integer id, 
    		@Param("account") String account, 
    		@Param("mName") String mName, 
    		@Param("sName") String sName);
}