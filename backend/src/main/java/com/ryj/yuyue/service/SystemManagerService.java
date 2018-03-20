package com.ryj.yuyue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.SystemManager;
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
	 * 插入提条系统管理员的记录
	 * @param sysManager
	 */
	public void insertASystemManager(SystemManager sysManager) {
		sysManagerMapper.insertSelective(sysManager);
	}
}
