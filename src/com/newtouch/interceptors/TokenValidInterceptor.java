package com.newtouch.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.utils.Constants;
import com.newtouch.utils.TokenUtil;
/**
 * 用于验证token
 * @author 赵东朝
 * 2015年12月2日
 */
public class TokenValidInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		   if(!TokenUtil.validToken(request)){
	            response.sendRedirect(Constants.DEFAULT_TOKEN_MSG_JSP);
	            return false ;
	        }
		    return true ;
	}

}
