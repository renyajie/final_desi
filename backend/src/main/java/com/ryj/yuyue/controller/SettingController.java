package com.ryj.yuyue.controller;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryj.yuyue.bean.CardInfoResult;
import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.bean.CardKindResult;
import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.bean.ClassOrderResult;
import com.ryj.yuyue.bean.ClassTagResult;
import com.ryj.yuyue.bean.Place;
import com.ryj.yuyue.bean.Teacher;
import com.ryj.yuyue.bean.UserFeature;
import com.ryj.yuyue.service.CardService;
import com.ryj.yuyue.service.ClassService;
import com.ryj.yuyue.service.ManagerService;
import com.ryj.yuyue.service.OrderService;
import com.ryj.yuyue.service.PlaceService;
import com.ryj.yuyue.service.TagService;
import com.ryj.yuyue.service.TeacherService;
import com.ryj.yuyue.service.TransferService;
import com.ryj.yuyue.service.UserService;
import com.ryj.yuyue.utils.ConstantLiteral;
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
	private UserService userService;
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
	@Autowired
	private TransferService transferService;
	@Autowired
	private TagService tagService;
	
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
	 * @param classKind 课程种类
	 * @param tagOne 第一种标签：轻松，强烈，普通，三选一
	 * @param tagTwo 第二种标签：恢复，健体，二选一
	 * @param tagThree 第三种标签：调理，选或不选
	 * @param tagFour 第四种标签：消耗，选或不选
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addClassKind", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addClassKind(
			@Valid ClassKind classKind, 
			@RequestParam("tagOne") Integer tagOne,
			@RequestParam("tagTwo") Integer tagTwo,
			@RequestParam("tagThree") Integer tagThree,
			@RequestParam("tagFour") Integer tagFour,
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
		//添加课程种类
		classService.addClassKind(classKind);
		//添加课程标签
		tagService.addClassTag(tagOne, tagTwo, tagThree, tagFour, classKind.getId());
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
	 * 更新课程标签
	 * @param id
	 * @param tagOne
	 * @param tagTwo
	 * @param tagThree
	 * @param tagFour
	 * @return
	 */
	@RequestMapping(value = "updateClassTag", method = RequestMethod.POST)
	@ResponseBody
	public Messenger updateClassTag(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "tagOne", required = true) Integer tagOne,
			@RequestParam(value = "tagTwo", required = true) Integer tagTwo,
			@RequestParam(value = "tagThree", required = true) Integer tagThree,
			@RequestParam(value = "tagFour", required = true) Integer tagFour) {
		
		tagService.updateClassTag(tagOne, tagTwo, tagThree, tagFour, id);
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

		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		
		List<ClassKindResult> result = null;
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 10);
			result = classService.getClassKind(
					placeId, classKId, managerId, classKName, property, difficulty);
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		result = classService.getClassKind(
				placeId, classKId, managerId, classKName, property, difficulty);
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 添加用户特征
	 * @param userFeature
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addUserFeature", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addUserFeature(
			@RequestBody @Valid UserFeature userFeature, 
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
		
		userService.addUserFeature(userFeature);
		return Messenger.success();
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
			@RequestParam(value = "placeId", required = false) Integer placeId,
			@RequestParam(value = "cardKName", required = false) String cardKName, 
			@RequestParam(value = "capacity", required = false) Integer capacity, 
			@RequestParam(value = "expend", required = false) Integer expend,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		
		List<CardKindResult> result = null;
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 10);
			result = cardService.getCardKind(
					cardKId, managerId, placeId, cardKName, capacity, expend);
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		result = cardService.getCardKind(
				cardKId, managerId, placeId, cardKName, capacity, expend);
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
	 * 获取课程标签
	 * @param pn
	 * @param placeId
	 * @param classKId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="getClassTag", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getClassTag(
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "placeId", required = false ) Integer placeId,
			@RequestParam(value = "classKindId", required = false) Integer classKId) {
		
		PageHelper.startPage(pn, 10);
		List<ClassTagResult> result = tagService.getClassTag(placeId, classKId);
		PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
		return Messenger.success().add("pageInfo", page);
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
			@RequestParam(value = "placeId", required = false) Integer placeId,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		PageHelper.startPage(pn, 10);
		List<CardInfoResult> result = this.cardService.getCardInfo(
				managerId, cardKId, userId, placeId);
		//判断是否需要分页
		if(isPage == 1) {
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		logger.info("getCardInfo, without page");
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
		
		PageHelper.startPage(pn, 9);
		PageInfo page = new PageInfo(
				managerService.getManagerList(id, account, mName, sName), ConstantLiteral.PAGE_SIZE);
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
	 * 获取一个会员卡信息
	 * @param cardId 会员卡编号
	 * @return
	 */
	@RequestMapping(value = "getOneCardInfo", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getOneCardInfo(
			@RequestParam(value = "cardId", required=true) Integer cardId) {
		
		CardInfoResult result = cardService.getOneCardInfo(cardId);
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取一个课程标签
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getOneClassTag", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getOneClassTag(
			@RequestParam(value = "id", required=true) Integer id) {
		
		ClassTagResult result = tagService.getOneClassTag(id);
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取一个会员卡种类信息
	 * @param cardKId 会员卡种类编号
	 * @return
	 */
	@RequestMapping(value = "getOneCardKind", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getOneCardKind(
			@RequestParam(value = "cardKId", required=true) Integer cardKId) {
		
		CardKindResult result = cardService.getOneCardKind(cardKId);
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
	 * @param image 上传的图片，可以为空
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updatePlace", method = RequestMethod.POST)
	@ResponseBody
	public Messenger updatePlace(
			@Valid Place place,
			@RequestParam(value="image", required=false) MultipartFile image,
			BindingResult syntaxResult) {
		
		logger.info("add place params: place is {}, image size is {}", place, image.getSize());
		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		if(image != null) {
			String picUrl = transferService.addPlaceImage(place.getId(), image);
			place.setPicUrl(picUrl);
		}
		
		placeService.updatePlace(place);
		return Messenger.success();
	}
	
	/**
	 * 更新场馆信息，不带图片
	 * @param place
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updatePlaceSimple", method = RequestMethod.POST)
	@ResponseBody
	public Messenger updatePlaceSimple(
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

		List<Place> result = new ArrayList<Place>();
		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		if(isPage == 1) {
			PageHelper.startPage(pn, 9);
			result = placeService.getPlace(id, sName, address, phone);
		}
		//防止对不分页的情况限制了数据量
		else {
			result = placeService.getPlace(id, sName, address, phone);
		}
		//判断是否需要分页
		if(isPage == 1) {
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
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
	
	/**
	 * 获取用户特征
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value="getOneUserFeature", method=RequestMethod.GET)
	@ResponseBody
	public Messenger getOneUserFeature(
			@RequestParam(value = "userId", required = true) Integer userId) {
		
		return Messenger.success().add("info", 
				userService.getUserFeature(userId));
	}
}
