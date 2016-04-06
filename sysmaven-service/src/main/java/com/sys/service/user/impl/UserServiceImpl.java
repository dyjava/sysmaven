package com.sys.service.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sys.dao.user.UserDao;
import com.sys.domain.user.User;
import com.sys.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao dao ;
	
	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 查询所有用户信息
	 */
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return dao.selectUsersByUser(new User()) ;
	}

	/**
	 * 查询单条用户信息
	 */
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return dao.selectUserByID(id) ;
	}

	/**
	 * 删除一条用户信息
	 */
	public boolean userDelete(int id) {
		// TODO Auto-generated method stub
		return dao.deleteUserByID(id)==0 ;
	}

	/**
	 * 用户注册
	 */
	public int userRegister(User user) {
		// TODO Auto-generated method stub
		if(user.getUsername()==null || user.getUsername().trim().length()==0){
			user.setUsername(user.getEmail().substring(0,user.getEmail().indexOf("@"))) ;
		}
		return dao.insertUser(user) ;
	}

	/**
	 * 用户信息修改
	 */
	public int userUpdate(User user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user) ;
	}
	
	/**
	 * 按条件查询
	 */
	public List<User> getUsersByUser(User user) {
		// TODO Auto-generated method stub
		return dao.selectUsersByUser(user);
	}
	
	/**
	 * 修改密码
	 */
	public int userPasswordUpdate(User user) {
		// TODO Auto-generated method stub
		user.setPassword(user.getPassword()) ;
		return dao.updateUserPwd(user) ;
	}
	/**
	 * 用户登录
	 */
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
