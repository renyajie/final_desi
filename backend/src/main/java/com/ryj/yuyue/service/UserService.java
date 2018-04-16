package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.bean.UserExample;
import com.ryj.yuyue.bean.UserExample.Criteria;
import com.ryj.yuyue.bean.UserFeature;
import com.ryj.yuyue.bean.UserFeatureExample;
import com.ryj.yuyue.dao.UserFeatureMapper;
import com.ryj.yuyue.dao.UserMapper;

/**
 * 处理和用户相关的服务
 * 1. 个人信息
 * 2. 个人特征
 * @author Thor
 *
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserFeatureMapper userFeatureMapper;
	
	/**
	 * 插入用户特征
	 * @param userFeature
	 */
	public void addUserFeature(UserFeature userFeature) {
		userFeatureMapper.insertSelective(userFeature);
	}
	
	/**
	 * 获取用户特征编号
	 * @param userId
	 * @return
	 */
	public UserFeature getUserFeature(Integer userId) {
		UserFeatureExample example = new UserFeatureExample();
		com.ryj.yuyue.bean.UserFeatureExample.Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(userId);
		return userFeatureMapper.selectByExample(example).get(0);
	}
	
	/**
	 * 更新用户特征
	 * @param userFeature
	 */
	public void updateUserFeature(UserFeature userFeature) {
		userFeatureMapper.insertSelective(userFeature);
	}
	
	/**
	 * 检查账号和密码是否正确
	 * @param user
	 * @return 登录失败null，登陆成功返回User信息
	 */
	public User checkLogin(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(user.getPhone());
		criteria.andPasswdEqualTo(user.getPasswd());
		List<User> result = userMapper.selectByExample(example);
		
		if(result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	/**
	 * 检查手机号是否重复， 如果出现重复，则返回true，不重复返回false
	 * @param username
	 * @return
	 */
	public boolean checkPhoneExist(String phone) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		int size = userMapper.selectByExample(example).size();
		return size == 0 ? false : true;
	}
	
	/**
	 * 用户注册
	 * @param user
	 */
	public User register(User user) {
		userMapper.insertSelective(user);
		return user;
	}
	
	/**
	 * 用户更新信息
	 * @param user
	 */
	public void update(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	/**
	 * 获取用户基本信息
	 * @param id
	 * @return
	 */
	public User getUserInfo(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询用户信息，根据用户编号，手机，名称模糊查询
	 * @param id 用户编号
	 * @param phone 用户手机
	 * @param uName 用户名称
	 * @return
	 */
	public List<User> getUser(Integer id, String phone, String uName) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(id != null) {
			criteria.andIdEqualTo(id);
		}
		if(phone != null && phone.length() != 0) {
			criteria.andPhoneLike("%" + phone + "%");
		}
		if(uName != null && uName.length() != 0) {
			criteria.andUNameLike("%" + uName + "%");
		}
		return userMapper.selectByExample(example);
	}
	
}
