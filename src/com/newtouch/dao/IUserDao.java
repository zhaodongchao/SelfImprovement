package com.newtouch.dao;

import java.util.List;

import com.newtouch.entitys.User;

public interface IUserDao {
	/**
	 * 简单查询
	 * @param user
	 * @return
	 */
    List<User> getUserByCondition(User user);
}
