package com.newtouch.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.newtouch.base.BaseDao;
@Repository
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{
   
	/**
	 * 
	 * 获取传过来的泛型类名字
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getClassName(){
		//在父类中得到子类声明的父类的泛型信息  
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
		return clazz.getSimpleName().toString().toLowerCase();
	}
	
	public int deleteByCondition(T t) {
		return this.getSqlSession().delete(this.getClassName()+".deleteByCondition", t);
	}

	public int insert(T record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<T> findByCondition(T t) {
		
		return this.getSqlSession().selectList(this.getClassName()+".query", t);
	}

	public int updateByPrimaryKeySelective(T t) {
		// TODO Auto-generated method stub
		return 0;
	}



}
