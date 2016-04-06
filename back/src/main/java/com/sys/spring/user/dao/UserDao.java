package com.sys.spring.user.dao;

import java.util.List;

import com.sys.spring.user.domain.User;

/**
 * 用户管理
 * @author Administrator
 *
 */
public interface UserDao {

	public abstract int insertUser(User user) ;	//新增用户
	public abstract int updateUser(User user) ;	//修改用户信息
	public abstract int deleteUserByID(int userid) ;	//删除ID用户
	public abstract User selectUserByID(int userid) ;	//按ID查询用户
	public abstract List<User> selectUsersByUser(User user) ;	//按条件查询
	public abstract int updateUserPwd(User user) ;	//修改密码
}
