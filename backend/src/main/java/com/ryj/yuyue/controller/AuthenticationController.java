package com.ryj.yuyue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryj.yuyue.bean.Manager;
import com.ryj.yuyue.bean.SystemManager;
import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.service.ManagerService;
import com.ryj.yuyue.service.SystemManagerService;
import com.ryj.yuyue.service.UserService;
import com.ryj.yuyue.utils.ConstantLiteral;
import com.ryj.yuyue.utils.Messenger;

/**
 * 处理所有人员的登录，注册和信息更新：
 * 
 * 1. 用于登录，注册，更新信息
 * 2. 管理员登录，注册，更新信息
 * 3. 系统管理员登录，修改信息
 * @author Thor
 *
 */
@Controller
@RequestMapping("api/auth")
public class AuthenticationController {

	private static final Logger logger = 
			LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private SystemManagerService systemManagerService;
	
	/**
	 * 用户登录
	 * @param user 用户信息
	 * @return
	 */
	@RequestMapping(value="userLogIn", method=RequestMethod.POST)
	@ResponseBody
	public Messenger userLogIn(@RequestBody User user) {
		logger.info("user login---phone is {}", user.getPhone());
		//校验账号，密码是否为空
		if(user.getPhone() == null 
				|| user.getPhone().length() == 0
				|| user.getPasswd() == null
				|| user.getPasswd().length() == 0){
			return Messenger.fail().setMsg(ConstantLiteral.NULL_INFO);
		}
		//检查登录信息
		User result = userService.checkLogin(user);
		if(result == null) {
			return Messenger.fail().setMsg(ConstantLiteral.LOGIN_FAILURE);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 管理员登录
	 * @param manager 用户信息
	 * @return
	 */
	@RequestMapping(value="managerLogIn", method=RequestMethod.POST)
	@ResponseBody
	public Messenger managerLogIn(@RequestBody Manager manager) {
		logger.info("manager login---account is {}", manager.getAccount());
		//校验账号，密码是否为空
		if(manager.getAccount() == null 
				|| manager.getAccount().length() == 0
				|| manager.getPasswd() == null
				|| manager.getPasswd().length() == 0){
			return Messenger.fail().add(ConstantLiteral.INFO, ConstantLiteral.NULL_INFO);
		}
		//检查登录信息
		Manager result = managerService.checkLogin(manager);
		if(result == null) {
			return Messenger.fail().add(ConstantLiteral.INFO, ConstantLiteral.LOGIN_FAILURE);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 系统管理员登录
	 * @param manager 用户信息
	 * @return
	 */
	@RequestMapping(value="sysManagerLogIn", method=RequestMethod.POST)
	@ResponseBody
	public Messenger systemManagerLogIn(@RequestBody SystemManager sysManager) {
		logger.info("sysManager login---account is {}", sysManager.getAccount());
		//校验账号，密码是否为空
		if(sysManager.getAccount() == null 
				|| sysManager.getAccount().length() == 0
				|| sysManager.getPasswd() == null
				|| sysManager.getPasswd().length() == 0){
			return Messenger.fail().add(ConstantLiteral.INFO, ConstantLiteral.NULL_INFO);
		}
		//检查登录信息
		SystemManager result = systemManagerService.checkLogin(sysManager);
		if(result == null) {
			return Messenger.fail().add(ConstantLiteral.INFO, ConstantLiteral.LOGIN_FAILURE);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 用户注册
	 * @param user 用户信息
	 * @param syntaxResult 校验信息
	 * @return
	 */
	@RequestMapping(value="userRegister", method=RequestMethod.POST)
	@ResponseBody
	public Messenger userRegister(
			@RequestBody @Valid User user, 
			BindingResult syntaxResult) {
		logger.info("user register---user is {}", user);
		//校验字段格式
		if(syntaxResult.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		//检查手机号是否重复
		if(userService.checkPhoneExist(user.getPhone())) {
			return Messenger.fail().setMsg("手机号已被注册");
		}
		//注册信息
		userService.register(user);
		return Messenger.success().setMsg(user.getId() + "");
	}
	
	/**
	 * 管理员注册
	 * @param manager
	 * @param syntaxResult 校验信息
	 * @return
	 */
	@RequestMapping(value="managerRegister", method=RequestMethod.POST)
	@ResponseBody
	public Messenger managerRegister(
			@RequestBody @Valid Manager manager, 
			BindingResult syntaxResult) {
		logger.info("manager register---manager is {}", manager);
		//校验字段格式
		if(syntaxResult.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		//注册信息，判断账号是否重复
		if(!managerService.register(manager)) {
			return Messenger.fail().add("other", "账号已被注册");
		}
		return Messenger.success();
	}
	
	/**
	 * 系统管理员注册
	 * @param sysManager
	 * @param syntaxResult 校验信息
	 * @return
	 */
	/*
	@RequestMapping(value="sysManagerRegister", method=RequestMethod.POST)
	@ResponseBody
	public Messenger sysManagerRegister(
			@RequestBody @Valid SystemManager sysManager, 
			BindingResult syntaxResult) {
		logger.info("sysManager register---sysManager is {}", sysManager);
		//校验字段格式
		if(syntaxResult.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		//注册信息
		//systemManagerService.register(sysManager);
		return Messenger.success();
	}
	*/
	
	/**
	 * 用户更新个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="userUpdate", method=RequestMethod.PUT)
	@ResponseBody
	public Messenger userUpdate(
			@RequestBody @Valid User user, 
			BindingResult syntaxResult) {
		//校验字段格式
		if(syntaxResult.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		logger.info("user info update is {}", user);
		userService.update(user);
		return Messenger.success();
	}
	
	/**
	 * 管理员更新个人信息
	 * @param manager
	 * @return
	 */
	@RequestMapping(value="managerUpdate", method=RequestMethod.PUT)
	@ResponseBody
	public Messenger managerUpdate(
			@RequestBody @Valid Manager manager, 
			BindingResult syntaxResult) {
		//校验字段格式
		if(syntaxResult.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		logger.info("manager info update is {}", manager);
		managerService.update(manager);
		return Messenger.success();
	}
	
	/**
	 * 系统管理员更新个人信息
	 * @param sysManager
	 * @return
	 */
	@RequestMapping(value="sysManagerUpdate", method=RequestMethod.PUT)
	@ResponseBody
	public Messenger sysManagerUpdate(
			@RequestBody @Valid SystemManager sysManager, 
			BindingResult syntaxResult) {
		//校验字段格式
		if(syntaxResult.hasErrors()){
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		logger.info("sysManager info update is {}", sysManager);
		systemManagerService.update(sysManager);
		return Messenger.success();
	}
	
	/**
	 * 获取某个系统管理员的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSysManagerInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getSysManagerInfo(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				systemManagerService.getSysManagerInfo(id));
	}
	
	/**
	 * 获取某个管理员的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getManagerInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getManagerInfo(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				managerService.getManagerInfo(id));
	}
	
	/**
	 * 获取某个用户的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getUserInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getUserInfo(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				userService.getUserInfo(id));
	}
	
	/**
	 * 获取用户信息，手机和姓名模糊查询
	 * @param id 用户编号
	 * @param phone 手机
	 * @param uName 姓名
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="getUser", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getUser(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam(value = "uName", required = false) String uName) {
		logger.info("getUser uName is {}", uName);
		PageHelper.startPage(pn, 10);
		PageInfo page = new PageInfo(userService.getUser(id, phone, uName), ConstantLiteral.PAGE_SIZE);
		return Messenger.success().add("pageInfo", page);
	}
}
