package com.newtouch.services;

import java.util.List;
import java.util.Map;

import com.newtouch.entitys.User;


public interface ILoginService {

	List<Map<String,Object>> getMenus(String parentId);
	User judgeLogin(String username,String password);
}
