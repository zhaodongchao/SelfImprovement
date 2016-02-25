package com.newtouch.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.newtouch.base.impl.BaseDaoImpl;
import com.newtouch.dao.IUserDao;
import com.newtouch.entitys.User;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

	
     /**
      * 这个查询是级联查询
      */
	@Override
	public List<User> findByCondition(User t) {
	
		return super.findByCondition(t);
	}

	@Override
	public List<User> getUserByCondition(User user) {
		return this.getSqlSession().selectList("user.findUser", user);
	}
  
}
