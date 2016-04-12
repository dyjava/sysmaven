package com.sys.dao.user;

import java.io.Serializable;

import com.sys.dao.BaseDao;
import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.user.User;

/**
 * 用户管理
 * @author Administrator
 *
 */
@MyBatisRepository
public interface UserDao extends BaseDao<User, Serializable>{

}
