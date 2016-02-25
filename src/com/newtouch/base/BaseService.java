package com.newtouch.base;

import java.util.List;

import com.newtouch.entitys.User;

public interface BaseService<T> {
	List<User> findByCondition(T t);
}
