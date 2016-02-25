package com.newtouch.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newtouch.dao.impl.MenuDaoImpl;
import com.newtouch.entitys.Menu;
import com.newtouch.services.IMenuService;
@Service("menuServiceImpl")
public class MenuServiceImpl implements IMenuService{
    
	@Autowired
	private MenuDaoImpl menuDaoImpl ;
	@Override
	public List<Map<String,Object>> findByCondition(Menu mu) {
		List<Map<String,Object>> newlist = new ArrayList<>();
	    List<Menu> lmenu = menuDaoImpl.findByCondition(mu);
	    for(Menu menu :lmenu){
	    	Map<String,Object> mp = new HashMap<>();
	    	mp.put("id", menu.getId());
	    	mp.put("menu_name",menu.getMenu_name() );
	    	mp.put("level", menu.getLevel());
	    	mp.put("isleaf", menu.isIsleaf());
	    	mp.put("menu_url",menu.getMenu_url() );
	    	mp.put("created_time", menu.getCreated_time());
	    	mp.put("parentId", menu.getParentId());
	    	newlist.add(mp);
	    }
	    return newlist ;
	}

}
