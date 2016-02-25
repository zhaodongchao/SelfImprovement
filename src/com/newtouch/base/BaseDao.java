package com.newtouch.base;

import java.util.List;

import com.newtouch.entitys.Role;


/**
 * 
 * @author 赵东朝
 * 2015年11月29日
 */
public interface BaseDao<T> {

	    int deleteByCondition(T t);

	    int insert(T record);

	    int insertSelective(T t);
	  
	    List<T> findByCondition(T t);

	    int updateByPrimaryKeySelective(T t);


}
