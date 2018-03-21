package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.Manager;
import com.ryj.yuyue.bean.ManagerExample;
import com.ryj.yuyue.bean.ManagerExample.Criteria;
import com.ryj.yuyue.dao.ManagerMapper;

@Service
public class ManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	/**
	 * 检查账号和密码是否正确
	 * @param manager
	 * @return 登录失败null，登陆成功返回Manager信息
	 */
	public Manager checkLogin(Manager manager) {
		ManagerExample example = new ManagerExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(manager.getAccount());
		criteria.andPasswdEqualTo(manager.getPasswd());
		List<Manager> result = managerMapper.selectByExample(example);
		
		if(result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	/**
	 * 管理员注册
	 * @param manager
	 */
	public void register(Manager manager) {
		managerMapper.insertSelective(manager);
	}
	
	/**
	 * 管理员更新信息
	 * @param manager
	 */
	public void update(Manager manager) {
		managerMapper.updateByPrimaryKeySelective(manager);
	}
}
