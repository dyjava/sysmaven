package com.sys.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.common.CommUtil;
import com.sys.common.Logs;
import com.sys.dao.AbstractDBDao;
import com.sys.dao.user.UserDao;
import com.sys.domain.user.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDBDao implements UserDao {
	
	/**
	 * 用户删除
	 */
	@Override
	public int deleteUserByID(int userid) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		
		String sqlStr = "delete from user where ID=?" ;
		Object[] params = {userid} ;
		int result = super.update(sqlStr, params) ;
		
		buf.append("|").append(sqlStr)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}

	/**
	 * 用户添加
	 */
	@Override
	public int insertUser(User user) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		
		String sqlStr = "insert into user (UID,name,EMAIL,PASSWORD,USERNAME,REGISTDATE) values(?,?,?,?,?,?)" ;
		Object[] params = {CommUtil.getUUID(),user.getName(),user.getEmail()
				,user.getPassword(),user.getUsername(),CommUtil.getNowDate()} ;
		int result = update(sqlStr, params) ;

		buf.append("|").append(sqlStr)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}

	/**
	 * 按用户ID查询用户信息
	 */
	@Override
	public User selectUserByID(int id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		
		String sqlStr = "select * from user where id = ? ";
		Object[] params = {id} ;
		User result = super.selectObject(sqlStr,params, User.class) ;

		buf.append("|").append(sqlStr)
		.append("|").append(result.getId())
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}

	/**
	 * 按条件查询用户列表
	 */
	@Override
	public List<User> selectUsersByUser(User user) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		
		StringBuffer sqlStr = new StringBuffer("select * from user where 1=1" );
		ArrayList<Object> params = new ArrayList<Object>() ;
		if(user.getUsername()!=null){
			sqlStr.append(" and username like ?") ;
			params.add("%"+user.getUsername()+"%") ;
		}
		if(user.getEmail()!=null){
			sqlStr.append(" and email like ?") ;
			params.add(user.getEmail()) ;
		}
		if(user.getUid()!=null){
			sqlStr.append(" and uid = ?") ;
			params.add(user.getUid()) ;
		}
		if(user.getName()!=null){
			sqlStr.append(" and name = ?") ;
			params.add("%"+user.getName()+"%") ;
		}
		if(user.getRegistDate()!=null){
			sqlStr.append(" and registdate = ?") ;
			params.add(user.getRegistDate()) ;
		}
		List<User> result = super.selectList(sqlStr.toString(), params.toArray(), User.class) ;

		buf.append("|").append(sqlStr)
		.append("|").append(result!=null?result.size():0)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}

	/**
	 * 用户信息更新
	 */
	@Override
	public int updateUser(User user) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		
		StringBuffer sqlStr = new StringBuffer("update user set ") ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		if(user.getName()!=null){
			sqlStr.append(" name=?,") ;
			params.add(user.getName()) ;
		}
		if(user.getPassword()!=null){
			sqlStr.append(" PASSWORD=?,") ;
			params.add(user.getPassword()) ;
		}
		if(user.getEmail()!=null){
			sqlStr.append(" email=?,") ;
			params.add(user.getEmail()) ;
		}
		if(user.getUsername()!=null){
			sqlStr.append(" username=?,") ;
			params.add(user.getUsername()) ;
		}
		
		sqlStr.append(" modifydate=?,") ;
		params.add(CommUtil.getNowDate()) ;
		
		String sql = sqlStr.substring(0,sqlStr.length()-1)+ " where id=?" ;
		params.add(user.getId()) ;
		
		int result = update(sql, params.toArray()) ;

		buf.append("|").append(sqlStr)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}

	/**
	 * 修改密码
	 */
	@Override
	public int updateUserPwd(User user) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		
		StringBuffer sqlStr = new StringBuffer("update user set ") ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		sqlStr.append(" PASSWORD=?,modifydate=? where id=?") ;
		params.add(user.getPassword()) ;
		params.add(CommUtil.getNowDate()) ;
		params.add(user.getId()) ;
		
		int result = update(sqlStr.toString(), params.toArray()) ;

		buf.append("|").append(sqlStr)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}
	
}
