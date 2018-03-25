package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.bean.UserExample;
import com.ryj.yuyue.bean.UserExample.Criteria;
import com.ryj.yuyue.dao.UserMapper;

/**
 * 处理和用户相关的服务
 * @author Thor
 *
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
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
	 * 用户注册
	 * @param user
	 */
	public void register(User user) {
		userMapper.insertSelective(user);
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
