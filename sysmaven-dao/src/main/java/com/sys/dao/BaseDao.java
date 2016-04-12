package com.sys.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, PK extends Serializable> {
	
	public T selectById(Object id);
	
	public List<T> select(T record);
	
	public int insert(T record);

	public int updateById(T record);

	public int deleteById(Object id);
}
