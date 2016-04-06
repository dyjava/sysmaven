package com.sys.service.user;

import java.util.List;

import com.sys.domain.user.User;

public interface UserService {

	public int userRegister(User user) ;		//用户注册
	public int userUpdate(User user) ;			//用户详细信息更新
	public int userPasswordUpdate(User user) ;	//用户密码修改
	public boolean userDelete(int uid) ;		//删除用户
	public List<User> getAllUser() ;			//查询全部用户
	public User getUserById(int uid) ;			//按ID查询用户
	public List<User> getUsersByUser(User user) ;	//按条件查询用户
	public User userLogin(User user) ;			//按条件查询用户

	
}
