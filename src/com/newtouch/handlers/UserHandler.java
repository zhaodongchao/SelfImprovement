package com.newtouch.handlers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.entitys.User;
import com.newtouch.services.IUserService;
@RequestMapping("/userManager/")
@Controller
public class UserHandler {
	
	@Autowired
	private IUserService userServiceImpl ;
	
	@RequestMapping("list/users")
	@ResponseBody
   public Map<String,Object> getUserByCondition(){
	   return userServiceImpl.getUserByCondition(new User()) ;
   }
}
