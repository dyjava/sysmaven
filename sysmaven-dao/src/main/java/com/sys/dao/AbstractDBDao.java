package com.sys.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sys.common.Logs;

/**
 * jdbc公共方法
 * @author diyong
 *
 */
public abstract class AbstractDBDao {

	@Autowired
	private JdbcTemplate jdbc ;

	public JdbcTemplate getJdbc() {
		return jdbc;
	}
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	/**
	 * 按ID更新
	 * @param table
	 * @param id
	 * @param map
	 * @return
	 */
	public int updateById(String table,int id ,Map<String,Object> map){
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("updateById") ;
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("update ").append(table).append(" set id=?") ;
		
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(id) ;
		for(String key:map.keySet()){
			Object value = map.get(key) ;
			sql.append(",").append(key).append("=? ") ;
			params.add(value) ;
		}
		
		sql.append(" where id=?") ;
		params.add(id) ;
		
		int result = update(sql.toString(), params.toArray()) ;

		buf.append("|").append(sql)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}
	
	/**
	 * 删除一条记录
	 * @param table
	 * @param id
	 * @return
	 */
	public int deleteById(String table,int id){
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("deleteById") ;
		
		StringBuffer sql = new StringBuffer() ;
		sql.append("delete from ").append(table).append(" where id=").append(id) ;
		
		ArrayList<Object> params = new ArrayList<Object>() ;
		int result = update(sql.toString(), params.toArray()) ;

		buf.append("|").append(sql)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}
	
	/**
	 * 按ID查找一条
	 * @param id
	 * @return
	 */
	public <T> T findByID(String table,Object id,Class<?> T) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("selectByID") ;
		
		String sql = "select * from "+table+" where id ="+id;
		Object[] params = {} ;
		@SuppressWarnings("unchecked")
		T result = (T)selectObject(sql, params, T) ;

		buf.append("|").append(sql)
		.append("|")
		.append("|").append(System.currentTimeMillis()-start) ;
		Logs.info(buf.toString()) ;
		return result ;
	}
	
	/**
	 * 查询所有结果
	 * @param table
	 * @param T
	 * @return
	 */
	public <T> List<T> findAllList(String table, Class<?> T) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findAllList") ;
		
		String sql = "select * from "+table ;
		
		List<T> list = this.selectList(sql ,new Object[] {} , T) ;

		buf.append("|").append(sql)
		.append("|").append("")
		.append("|").append(list.size())
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		return list;
	}
	
	/**
	 * 更新和添加
	 * @param sql
	 * @param params
	 * @return
	 */
	protected int update(String sql,Object[] params){
		if(params==null || params.length==0){
			return jdbc.update(sql) ;
		}
		return jdbc.update(sql, params) ;
	}
	
	/**
	 * 查询单条结果
	 * @param sql
	 * @param params
	 * @param T
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T selectObject(String sql,Object[] params,Class<?> T){
		List<T> result = selectList(sql, params, T) ;
		if(result.size()>0){
			return result.get(0) ;
		}
		try {
			return (T) T.newInstance() ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 查询多条结果
	 * @param sql
	 * @param params
	 * @param T
	 * @return
	 */
	protected <T> List<T> selectList(String sql,Object[] params,Class<?> T){
		List<?> list ;
		if(params==null || params.length==0){
			list = jdbc.queryForList(sql) ;
		} else {
			list = jdbc.queryForList(sql,params) ;
		}
		if(list==null || list.size()==0){
			return new ArrayList<T>() ;
		}
		return queryToObjectList(list,T) ;
	}
	
	/**
	 * 结果列表转指定类型
	 * @param result
	 * @param T
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> queryToObjectList(List<?> result,Class<?> T) {
		List<T> list = new ArrayList<T>() ;
		
		Iterator<?> iterator = result.iterator();
		
		while (iterator.hasNext()) {
			Map<String, Object> itmap = (Map<String, Object>) iterator.next();
			try {
				T o = (T) T.newInstance();

				mapToBean(itmap, o, T) ;
				list.add(o) ;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list ;
	}
	
	/**
	 * 数据库返回map对象转指定类型
	 * @param map
	 * @param bean
	 * @param clas
	 */
	private void mapToBean(Map<String,Object> map,Object bean,Class<?> clas){
		if(!clas.getSuperclass().equals(Object.class)){
			this.mapToBean(map, bean, clas.getSuperclass()) ;
		}
		Field[] fs = clas.getDeclaredFields();	//获取私有属性。
		for(Field f:fs){
			if(map.containsKey(f.getName())){
				f.setAccessible(true);//设置私有、保护变量的可以访问权限。
				try {
					if(map.get(f.getName())!=null){
						String item = String.valueOf(map.get(f.getName())) ;
						if(f.getType().getName().equals("java.lang.String")){
							f.set(bean, (String)item) ;
						} else if(f.getType().getName().equals("int")){
							f.set(bean, Integer.parseInt(item)) ;
						} else if(f.getType().getName().equals("double")){
							f.set(bean, Double.parseDouble(item)) ;
						} else if(f.getType().getName().equals("long")){
							f.set(bean, Long.parseLong(item)) ;
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 打印日志用的
	 * @param list
	 * @return
	 */
	protected String list2String(List<Object> list){
		StringBuffer buf = new StringBuffer() ;
		for(Object obj:list){
			buf.append(obj).append(",") ;
		}
		return buf.toString() ;
	}
}
