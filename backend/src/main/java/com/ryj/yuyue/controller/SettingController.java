package com.ryj.yuyue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.Place;
import com.ryj.yuyue.bean.Teacher;
import com.ryj.yuyue.service.CardService;
import com.ryj.yuyue.service.ClassService;
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

	@Autowired
	private ClassService classService;
	@Autowired
	private CardService cardService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PlaceService placeService;
	
	/**
	 * 管理员增加课程信息
	 * @param classInfo
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addClassInfo", method = RequestMethod.POST)
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
	 * @return
	 */
	@RequestMapping(value = "getClassKind", method = RequestMethod.GET)
	public Messenger getClassKind(
			@RequestParam(value = "classKId", required = false) Integer classKId, 
			@RequestParam(value = "managerId", required = false) Integer managerId, 
			@RequestParam(value = "kName", required = false) String kName, 
			@RequestParam(value = "property", required = false) String property) {

		return Messenger.success().add("info", 
				classService.getClassKind(
						classKId, managerId, kName, property));
	}

	/**
	 * 管理员添加会员卡种类
	 * @param cardKind
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addCardKind", method = RequestMethod.POST)
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
	 * @param cardKId
	 * @param pId
	 * @param cardKName
	 * @param capacity
	 * @param expend
	 * @return
	 */
	@RequestMapping(value = "getCardKind", method = RequestMethod.GET)
	public Messenger getCardKind( 
			@RequestParam(value = "cardKId", required = false) Integer cardKId, 
			@RequestParam(value = "pId", required = false) Integer pId, 
			@RequestParam(value = "cardKName", required = false) String cardKName, 
			@RequestParam(value = "capacity", required = false) Integer capacity, 
			@RequestParam(value = "expend", required = false) Integer expend) {

		return Messenger.success().add("info", 
				cardService.getCardKind(
						cardKId, pId, cardKName, capacity, expend));
	}

	/**
	 * 管理员添加教师信息
	 * @param teacher
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addTeacher", method = RequestMethod.POST)
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
	 * 管理员获取场馆信息
	 * @param id
	 * @param placeName
	 * @return
	 */
	@RequestMapping(value = "getPlace", method = RequestMethod.GET)
	public Messenger getPlace( 
			@RequestParam(value = "id", required = false) Integer id, 
			@RequestParam(value = "placeName", required = false) String placeName) {

		return Messenger.success().add("info", 
				placeService.getPlace(id, placeName));
	}
}
