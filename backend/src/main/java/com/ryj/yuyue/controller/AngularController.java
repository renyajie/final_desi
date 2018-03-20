package com.ryj.yuyue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryj.yuyue.bean.SystemManager;
import com.ryj.yuyue.service.SystemManagerService;
import com.ryj.yuyue.utils.Messenger;

/**
 * 测试Angular
 * @author Thor
 *
 */
@Controller
@RequestMapping("api/test/angular")
public class AngularController {
	
	private static final Logger logger = LoggerFactory.getLogger(AngularController.class);
	
	@Autowired
	private SystemManagerService systemManagerService;
	
	/**
	 * 插入一条系统管理员记录
	 * @param sysManager
	 * @return
	 */
	@RequestMapping("addSysManager")
	@ResponseBody
	public Messenger addASystemManager(@RequestBody SystemManager sysManager) {
		logger.info("add a system manager, account is {}, password is {}", 
				sysManager.getAccount(), sysManager.getPasswd());
		systemManagerService.insertASystemManager(sysManager);
		return Messenger.success().add("info", "成功");
	}
}
