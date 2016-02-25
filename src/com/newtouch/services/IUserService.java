package com.newtouch.services;

import java.util.List;
import java.util.Map;

import com.newtouch.entitys.User;

public interface IUserService {
	List<User> findByCondition(User user);
    public Map<String,Object> getUserByCondition(User user);
}
