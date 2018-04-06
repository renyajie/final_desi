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
import com.ryj.yuyue.bean.CardInfoResult;
import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.bean.CardKindResult;
import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.bean.ClassOrderResult;
import com.ryj.yuyue.bean.Place;
import com.ryj.yuyue.bean.Teacher;
import com.ryj.yuyue.service.CardService;
import com.ryj.yuyue.service.ClassService;
import com.ryj.yuyue.service.ManagerService;
import com.ryj.yuyue.service.OrderService;
import com.ryj.yuyue.service.PlaceService;
import com.ryj.yuyue.service.TeacherService;
import com.ryj.yuyue.utils.Messenger;

/**
 * 负责所有资源配置的功能
 * @author Thor
 *
 */
@Controller
@RequestMapping("api/setting")
public class SettingController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = 
			LoggerFactory.getLogger(SettingController.class);

	@Autowired
	private ClassService classService;
	@Autowired
	private CardService cardService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 管理员增加课程信息
	 * @param classInfo
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addClassInfo", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addClassInfo(
			@RequestBody @Valid ClassInfo classInfo, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		classService.addClassInfo(classInfo);
		return Messenger.success();
	}
	
	/**
	 * 管理员删除课程信息
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "deleteClassInfo", method = RequestMethod.DELETE)
	@ResponseBody
	public Messenger deleteClassInfo(
			@RequestParam(value = "classId", required = true) Integer classId) {
		
		classService.deleteClassInfo(classId);
		return Messenger.success();
	}
	
	/**
	 * 管理员更新课程信息
	 * @param classInfo
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updateClassInfo", method = RequestMethod.PUT)
	@ResponseBody
	public Messenger updateClassInfo(
			@RequestBody @Valid ClassInfo classInfo, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		classService.updateClassInfo(classInfo);
		return Messenger.success();
	}

	/**
	 * 管理员添加课程种类
	 * @param classKind
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addClassKind", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addClassKind(
			@RequestBody @Valid ClassKind classKind, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		classService.addClassKind(classKind);
		return Messenger.success();
	}
	
	/**
	 * 管理员删除课程种类
	 * @param classKId
	 * @return
	 */
	@RequestMapping(value = "deleteClassKind", method = RequestMethod.DELETE)
	@ResponseBody
	public Messenger deleteClassKind(
			@RequestParam(value = "classKId", required = true) Integer classKId) {
		
		classService.deleteClassKind(classKId);
		return Messenger.success();
	}

	/**
	 * 管理员更新课程种类
	 * @param classKind
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updateClassKind", method = RequestMethod.PUT)
	@ResponseBody
	public Messenger updateClassKind(
			@RequestBody @Valid ClassKind classKind, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		classService.updateClassKind(classKind);
		return Messenger.success();
	}

	/**
	 * 管理员获取课程种类
	 * @param classKId
	 * @param managerId
	 * @param kName
	 * @param property
	 * @param difficulty
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getClassKind", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getClassKind(
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "placeId", required = false) Integer placeId,
			@RequestParam(value = "classKId", required = false) Integer classKId, 
			@RequestParam(value = "managerId", required = false) Integer managerId, 
			@RequestParam(value = "classKName", required = false) String classKName, 
			@RequestParam(value = "property", required = false) String property,
			@RequestParam(value = "difficulty", required = false) Integer difficulty,
			@RequestParam(value = "isPage", required = true) Integer isPage) {

		List<ClassKindResult> result = classService.getClassKind(
				placeId, classKId, managerId, classKName, property, difficulty);
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 5);
			PageInfo page = new PageInfo(result, 5);
			return Messenger.success().add("pageInfo", page);
		}
		return Messenger.success().add("info", result);
	}

	/**
	 * 管理员添加会员卡种类
	 * @param cardKind
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addCardKind", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addCardKind(
			@RequestBody @Valid CardKind cardKind, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		cardService.addCardKind(cardKind);
		return Messenger.success();
	}
	
	/**
	 * 管理员删除会员卡种类
	 * @param cardKId
	 * @return
	 */
	@RequestMapping(value = "deleteCardKind", method = RequestMethod.DELETE)
	@ResponseBody
	public Messenger deleteCardKind(
			@RequestParam(value = "cardKId", required = true) Integer cardKId) {
		
		cardService.deleteCardKind(cardKId);
		return Messenger.success();
	}

	/**
	 * 管理员更新会员卡种类
	 * @param cardKind
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updateCardKind", method = RequestMethod.PUT)
	@ResponseBody
	public Messenger updateCardKind(
			@RequestBody @Valid CardKind cardKind, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		cardService.updateCardKind(cardKind);
		return Messenger.success();
	}

	/**
	 * 管理员或用户获取会员卡种类
	 * @param pn
	 * @param cardKId
	 * @param managerId
	 * @param cardKName
	 * @param capacity
	 * @param expend
	 * @param isPage
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getCardKind", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getCardKind( 
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "cardKId", required = false) Integer cardKId, 
			@RequestParam(value = "managerId", required = false) Integer managerId, 
			@RequestParam(value = "cardKName", required = false) String cardKName, 
			@RequestParam(value = "capacity", required = false) Integer capacity, 
			@RequestParam(value = "expend", required = false) Integer expend,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		List<CardKindResult> result = cardService.getCardKind(
				cardKId, managerId, cardKName, capacity, expend);
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 5);
			PageInfo page = new PageInfo(result, 5);
			return Messenger.success().add("pageInfo", page);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取某种会员卡的详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getCardKindInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getCardKindInfo(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				cardService.getCardKindInfo(id));
	}
	
	/**
	 * 获取某个课程种类的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getClassKindInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getClassKindInfo(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				classService.getClassKindInfo(id));
	}
	
	/**
	 * 获取某个课程信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getOneClassInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getOneClassInfo(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				classService.getOneClassInfo(id));
	}
	
	/**
	 * 获取会员卡信息
	 * @param pn
	 * @param cardKId
	 * @param uId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="getCardInfo", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getCardInfo(
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "managerId", required = false ) Integer managerId,
			@RequestParam(value = "cardKId", required = false) Integer cardKId, 
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		List<CardInfoResult> result = this.cardService.getCardInfo(
				managerId, cardKId, userId);
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 5);
			PageInfo page = new PageInfo(result, 5);
			return Messenger.success().add("pageInfo", page);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取管理员列表
	 * @param pn 第几页
	 * @param id 编号
	 * @param account 账号
	 * @param mName 姓名
	 * @param sName 地点名称
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="getManagerList", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getManagerList(
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "id", required = false ) Integer id,
			@RequestParam(value = "account", required = false) String account, 
			@RequestParam(value = "mName", required = false) String mName,
			@RequestParam(value = "sName", required = false) String sName) {
		
		PageHelper.startPage(pn, 5);
		PageInfo page = new PageInfo(
				managerService.getManagerList(id, account, mName, sName), 5);
		return Messenger.success().add("pageInfo", page);
	}
	
	/**
	 * 获取一个课程订单
	 * @param orderId 订单编号
	 * @return
	 */
	@RequestMapping(value = "getOneClassOrder", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getOneClassOrder(
			@RequestParam(value = "orderId", required=false) Integer orderId) {
		
		ClassOrderResult result = orderService.getOneClassOrder(orderId);
		return Messenger.success().add("info", result);
	}

	/**
	 * 管理员添加教师信息
	 * @param teacher
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addTeacher", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addTeacher(
			@RequestBody @Valid Teacher teacher, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		teacherService.addTeacher(teacher);
		return Messenger.success();
	}

	/**
	 * 管理员删除教师信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteTeacher", method = RequestMethod.DELETE)
	@ResponseBody
	public Messenger deleteTeacher(
			@RequestParam(value = "id", required = true) Integer id) {
		
		teacherService.deleteTeacher(id);
		return Messenger.success();
	}

	/**
	 * 管理员更新个人信息
	 * @param teacher
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updateTeacher", method = RequestMethod.PUT)
	@ResponseBody
	public Messenger updateTeacher(
			@RequestBody @Valid Teacher teacher, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		teacherService.updateTeacher(teacher);
		return Messenger.success();
	}
	
	/**
	 * 管理员获取教师信息
	 * @param id
	 * @param pId
	 * @param teacherName
	 * @return
	 */
	@RequestMapping(value = "getTeacher", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getTeacher( 
			@RequestParam(value = "id", required = false) Integer id, 
			@RequestParam(value = "pId", required = false) Integer pId, 
			@RequestParam(value = "teacherName", required = false) String teacherName) {

		return Messenger.success().add("info", 
				teacherService.getTeacher(id, pId, teacherName));
	}

	/**
	 * 管理员添加场馆
	 * @param place
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addPlace", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addPlace(
			@RequestBody @Valid Place place, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		placeService.addPlace(place);
		return Messenger.success();
	}

	/**
	 * 管理员删除场馆
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deletePlace", method = RequestMethod.DELETE)
	@ResponseBody
	public Messenger deletePlace(
			@RequestParam(value = "id", required = true) Integer id) {
		
		placeService.deletePlace(id);
		return Messenger.success();
	}

	/**
	 * 管理员更新场馆信息
	 * @param place
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updatePlace", method = RequestMethod.PUT)
	@ResponseBody
	public Messenger updatePlace(
			@RequestBody @Valid Place place, 
			BindingResult syntaxResult) {
		
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		placeService.updatePlace(place);
		return Messenger.success();
	}

	/**
	 * 获取场馆信息
	 * @param pn
	 * @param id
	 * @param sName
	 * @param address
	 * @param phone
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "getPlace", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getPlace( 
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "id", required = false) Integer id, 
			@RequestParam(value = "sName", required = false) String sName,
			@RequestParam(value = "address", required = false) String address, 
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "isPage", required = true) Integer isPage) {

		List<Place> result = placeService.getPlace(id, sName, address, phone);
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 5);
			PageInfo page = new PageInfo(result, 5);
			return Messenger.success().add("pageInfo", page);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取一个场馆信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getOnePlace", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getOnePlace(
			@RequestParam(value = "id", required = true) Integer id) {
		
		return Messenger.success().add("info", 
				placeService.getOnePlace(id));
	}
}
