package com.newtouch.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.newtouch.base.impl.BaseDaoImpl;
import com.newtouch.dao.ILoginDao;
import com.newtouch.entitys.Menu;
import com.newtouch.entitys.User;
@Repository("loginDaoImpl")
public class LoginDaoImpl extends BaseDaoImpl<Menu> implements ILoginDao{

	@Override
	public List<Menu> getMenus(String parentId) {
		return this.getSqlSession().selectList("menu.findMenu", parentId);
	}

	@Override
	public User judgeLogin(String username, String password) {
		User puser = new User();
		puser.setUsername(username);
		puser.setPassword(password);
		return this.getSqlSession().selectOne("user.findUser",puser);
	}
   

}
