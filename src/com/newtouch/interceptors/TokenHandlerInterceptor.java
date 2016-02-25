package com.newtouch.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.utils.TokenUtil;

/**
 * 用于生成token
 * @author 赵东朝
 * 2015年12月2日
 */
public class TokenHandlerInterceptor implements HandlerInterceptor{
   
	
	/**
	 * 在视图渲染之后调用，用于释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------>after");
		
	}
   /**
    *  在执行目标方法之后，渲染视图之前调用
    *  
    *  可以对请求域中的参数或视图进行修改
    */
   
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// 第一次请求，执行完目标方法后生成一个随机的token
		// TokenUtil.generateGUID(request.getSession());
		System.out.println("------>post-->"+ TokenUtil.generateGUID(request.getSession()));
		
	}
   /**
    * 在执行目标方法之前调用
    * 可以做权限，事物
    */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("------>pre");
		return true;
	}


}
