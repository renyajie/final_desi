package com.ryj.yuyue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryj.yuyue.bean.SystemManager;
import com.ryj.yuyue.bean.User;
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
	
	@RequestMapping(value="getSimpleMessage", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getSimpleMessage() {
		logger.info("getSimpleMessage");
		return Messenger.success().add("info", "GET方法: 简单消息");
	}
	
	@RequestMapping(value="getSimpleMessageWithParams", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getSimpleMessage(
			@RequestParam("name")String name) {
		logger.info("getSimpleMessageWithParam, name is {}", name);
		return Messenger.success().add("info", "带参数GET方法: 简单消息");
	}
	
	@RequestMapping(value="postParams", method=RequestMethod.POST)
	@ResponseBody
	public Messenger postParams(
			@RequestBody User user) {
		logger.info("postParams, name is {}", user.getuName());
		return Messenger.success().add("info", "POST方法: 简单消息");
	}
	
	@RequestMapping(value="putParams", method=RequestMethod.PUT)
	@ResponseBody
	public Messenger putParams(
			@RequestBody User user) {
		logger.info("putParams, name is {}", user.getuName());
		return Messenger.success().add("info", "PUT方法: 简单消息");
	}
	
	@RequestMapping(value="deleteParams", method=RequestMethod.POST)
	@ResponseBody
	public Messenger deleteParams(
			@RequestParam("name") String name) {
		logger.info("deleteParams, name is {}", name);
		return Messenger.success().add("info", "DELETE方法: 简单消息");
	}
	
	/**
	 * 插入一条系统管理员记录
	 * @param sysManager
	 * @return
	 */
	@RequestMapping(value="addSysManager", method=RequestMethod.POST)
	@ResponseBody
	public Messenger addASystemManager(@RequestBody SystemManager sysManager) {
		logger.info("add a system manager, account is {}, password is {}", 
				sysManager.getAccount(), sysManager.getPasswd());
		systemManagerService.insertASystemManager(sysManager);
		return Messenger.success().add("info", "成功");
	}
}
