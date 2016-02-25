package com.newtouch.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.dao.impl.RoleDaoImpl;
import com.newtouch.entitys.Role;
import com.newtouch.services.IRoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl implements IRoleService{
    @Autowired
	private RoleDaoImpl roleDaoImpl ;
	@Override
	public List<Role> ListRoles(Role role) {
    		return roleDaoImpl.findByCondition(role);
	}

}
