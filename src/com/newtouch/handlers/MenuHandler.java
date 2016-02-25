package com.newtouch.handlers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.entitys.Menu;
import com.newtouch.services.IMenuService;
@RequestMapping("/menuManager/")
@Controller
public class MenuHandler {
   @Autowired
	private IMenuService menuServiceImpl ;
   
   @ResponseBody
   @RequestMapping("list")
	public List<Map<String,Object>> listMenu(){
		return menuServiceImpl.findByCondition(new Menu());
	}
}
