package com.newtouch.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.base.impl.BaseDaoImpl;
import com.newtouch.entitys.Menu;
@Component("menuDaoImpl")
public class MenuDaoImpl extends BaseDaoImpl<Menu>{

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return super.getClassName();
	}

	@Override
	public int deleteByCondition(Menu t) {
		// TODO Auto-generated method stub
		return super.deleteByCondition(t);
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return super.insert(record);
	}

	@Override
	public int insertSelective(Menu t) {
		// TODO Auto-generated method stub
		return super.insertSelective(t);
	}

	@Override
	public List<Menu> findByCondition(Menu t) {
		// TODO Auto-generated method stub
		return super.findByCondition(t);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu t) {
		// TODO Auto-generated method stub
		return super.updateByPrimaryKeySelective(t);
	}
  
}
