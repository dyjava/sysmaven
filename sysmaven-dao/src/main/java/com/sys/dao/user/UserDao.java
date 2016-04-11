package com.sys.dao.user;

import java.util.List;

import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.user.User;

/**
 * 用户管理
 * @author Administrator
 *
 */
@MyBatisRepository
public interface UserDao {

	public int insertUser(User user) ;	//新增用户
	public int updateUser(User user) ;	//修改用户信息
	public int deleteUserByID(int userid) ;	//删除ID用户
	public User selectUserByID(int userid) ;	//按ID查询用户
	public List<User> selectUsersByUser(User user) ;	//按条件查询
	public int updateUserPwd(User user) ;	//修改密码
}
