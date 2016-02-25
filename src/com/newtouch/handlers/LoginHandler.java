package com.newtouch.handlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.newtouch.entitys.User;
import com.newtouch.services.ILoginService;
@RequestMapping(value={"sm/"})
@Controller
public class LoginHandler {
	
	@Autowired
	private ILoginService loginServiceImpl ;
    @RequestMapping(value="goLoginPage")
	public String goLoginPage(){
		return "sm/login/login";
	}
    @RequestMapping(value={"menu"})
    @ResponseBody
    public List<Map<String,Object>> getMenus(String node){
    	
    	return loginServiceImpl.getMenus(node) ;
    }
    
    @RequestMapping(value={"judgeLogin"})
    public ModelAndView judgeLogin(String username,String password,HttpSession session){
    	ModelAndView mv = new ModelAndView();
    	User user = loginServiceImpl.judgeLogin(username, password);
    	if(user!=null){
    		mv.setViewName("main");
    		mv.addObject("user", user);
    	}else{
    		
    		mv.setViewName("login");
    		mv.addObject("error","用户或密码错误");
    		
    	}
    	return mv;
    }

}
