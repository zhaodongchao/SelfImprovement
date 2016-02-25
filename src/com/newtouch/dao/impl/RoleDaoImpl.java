package com.newtouch.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.newtouch.base.impl.BaseDaoImpl;
import com.newtouch.entitys.Role;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role>{


	@Override
	public List<Role> findByCondition(Role t) {
		return super.findByCondition(t);
	}
 
}
