package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.SystemManager;
import com.ryj.yuyue.bean.SystemManagerExample;
import com.ryj.yuyue.bean.SystemManagerExample.Criteria;
import com.ryj.yuyue.dao.SystemManagerMapper;

/**
 * 和系统管理员有关的所有服务
 * @author Thor
 *
 */
@Service
public class SystemManagerService {

	@Autowired
	private SystemManagerMapper sysManagerMapper;
	
	/**
	 * 获取某个系统管理员的信息
	 * @param id
	 * @return
	 */
	public SystemManager getSysManagerInfo(Integer id) {
		return sysManagerMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 插入提条系统管理员的记录
	 * @param sysManager
	 */
	public void insertASystemManager(SystemManager sysManager) {
		sysManagerMapper.insertSelective(sysManager);
	}
	
	/**
	 * 检查账号和密码是否正确
	 * @param sysManager
	 * @return 登录失败null，登陆成功返回sysManager信息
	 */
	public SystemManager checkLogin(SystemManager sysManager) {
		SystemManagerExample example = new SystemManagerExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(sysManager.getAccount());
		criteria.andPasswdEqualTo(sysManager.getPasswd());
		List<SystemManager> result = sysManagerMapper.selectByExample(example);
		
		if(result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	/**
	 * 系统管理员注册
	 * @param sysManager
	 *
	public void register(SystemManager sysManager) {
		sysManagerMapper.insertSelective(sysManager);
	}*/
	
	/**
	 * 系统管理员更新信息
	 * @param sysManager
	 */
	public void update(SystemManager sysManager) {
		sysManagerMapper.updateByPrimaryKeySelective(sysManager);
	}
}
