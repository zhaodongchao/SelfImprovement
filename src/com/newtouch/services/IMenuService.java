package com.newtouch.services;

import java.util.List;
import java.util.Map;

import com.newtouch.entitys.Menu;

public interface IMenuService {
	List<Map<String,Object>> findByCondition(Menu mu) ;
}
