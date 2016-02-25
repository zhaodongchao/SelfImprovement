package com.newtouch.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newtouch.base.impl.BaseDaoImpl;
import com.newtouch.dao.ILoginDao;
import com.newtouch.entitys.Menu;
import com.newtouch.entitys.User;
import com.newtouch.services.ILoginService;
/**
@Transactional标注的位置
@Transactional注解可以标注在类和方法上，也可以标注在定义的接口和接口方法上。
如果我们在接口上标注@Transactional注解，会留下这样的隐患：因为注解不能被继承，
所以业务接口中标注的@Transactional注解不会被业务实现类继承。所以可能会出现不启动事务的情况。
所以，Spring建议我们将@Transaction注解在实现类上。
在方法上的@Transactional注解会覆盖掉类上的@Transactional。**/
@Transactional
@Service("loginServiceImpl")
public class LoginServiceImpl implements ILoginService {
    
	@Autowired
	private ILoginDao loginDaoImpl ;
	
	public List<Map<String,Object>> getMenus(String parentId) {
		List<Map<String,Object>> menus = new ArrayList<>();
		List<Menu> Menulist = loginDaoImpl.getMenus(parentId);
		for(Menu mu :Menulist){
			Map<String,Object> mp = new HashMap<>();
			mp.put("id", mu.getId());
			mp.put("text", mu.getMenu_name());
			mp.put("level", mu.getLevel());
			mp.put("leaf", mu.isIsleaf());
			mp.put("skiphref", mu.getMenu_url());
			menus.add(mp);
		}
		return menus;
	}

	@Override
	public User judgeLogin(String username, String password) {
		return loginDaoImpl.judgeLogin(username, password);
	}



	   

}
