package com.sys.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.user.UserDao;
import com.sys.domain.user.User;
import com.sys.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao ;
	
	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.select(new User()) ;
	}

	/**
	 * 查询单条用户信息
	 */
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id) ;
	}

	/**
	 * 删除一条用户信息
	 */
	@Override
	public boolean userDelete(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteById(id)==0 ;
	}

	/**
	 * 用户注册
	 */
	@Override
	public int userRegister(User user) {
		// TODO Auto-generated method stub
		if(user.getUsername()==null || user.getUsername().trim().length()==0){
			user.setUsername(user.getEmail().substring(0,user.getEmail().indexOf("@"))) ;
		}
		return userDao.insert(user) ;
	}

	/**
	 * 用户信息修改
	 */
	@Override
	public int userUpdate(User user) {
		// TODO Auto-generated method stub
		return userDao.updateById(user) ;
	}
	
	/**
	 * 按条件查询
	 */
	@Override
	public List<User> getUsersByUser(User user) {
		// TODO Auto-generated method stub
		return userDao.select(user);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public int userPasswordUpdate(User user) {
		// TODO Auto-generated method stub
		user.setPassword(user.getPassword()) ;
		return userDao.updateById(user) ;
	}
	/**
	 * 用户登录
	 */
	@Override
	public User userLogin(User user) {
		// TODO Auto-generated method stub
		List<User> list = this.getUsersByUser(user) ;
		
		if(list==null || list.size()!=1){
			return null ;
		} else {
			User _user = list.get(0) ;
			if(_user.getPassword().equals(user.getPassword())){
				return _user ;
			} else {
				return null ;
			}
		}
	}

}
