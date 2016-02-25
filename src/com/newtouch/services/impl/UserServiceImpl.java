package com.newtouch.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.dao.impl.UserDaoImpl;
import com.newtouch.entitys.Menu;
import com.newtouch.entitys.User;
import com.newtouch.services.IUserService;
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Autowired
	private UserDaoImpl userDaoImpl ;
	@Override
	public List<User> findByCondition(User user) {
		return userDaoImpl.findByCondition(user);
	}
	@Override
	public Map<String,Object> getUserByCondition(User user){
		Map<String,Object> result= new HashMap<>();
		  List<Map<String,? extends Object>> musers = new ArrayList<>();
			List<User> userList = userDaoImpl.getUserByCondition(user);
			for(User mu :userList){
				Map<String,Object> muser = new HashMap<>();
				muser.put("user_id", mu.getUser_id());
				muser.put("username", mu.getUsername());
				muser.put("password", mu.getPassword());
				muser.put("nickname", mu.getNickname());
				muser.put("age", mu.getAge());
				muser.put("sex", mu.isSex());
				muser.put("status", mu.isStatus());
			
				muser.put("telPhone", mu.getTelPhone());
				muser.put("company", mu.getCompany());
				muser.put("address", mu.getAddress());
				muser.put("mail", mu.getMail());
				
				muser.put("birthday", mu.getBirthday());
				muser.put("register_time", mu.getRegister_time());
				muser.put("last_login_time",mu.getLast_login_time());
				muser.put("parentId", mu.getParentId());
				
				musers.add(muser);
			}
			
			
			result.put("querydatas", musers);
			return result;
	}

}
