package com.newtouch.utils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class TokenUtil {
	private static Logger logger = Logger.getLogger(TokenUtil.class);
	static Map<String, String> springmvc_token = null;
	
	 @SuppressWarnings("unchecked")
	public synchronized static String generateGUID(HttpSession session) {
	        String token = "";
	        try {
	            Object obj =  session.getAttribute("newtouch.token");
	            if(obj!=null){
	              //  springmvc_token = (Map<String,String>)session.getAttribute("newtouch.token");
	                //如果Session中存在newtouch.token,就移除
	                session.removeAttribute("newtouch.token"); 
	                springmvc_token = new HashMap<String, String>();
	            }else
	               springmvc_token = new HashMap<String, String>();
	            token = new BigInteger(165, new Random()).toString(36).toUpperCase();//随机生成一个36位的字符串
	            springmvc_token.put(Constants.DEFAULT_TOKEN_NAME ,token);
	            session.setAttribute("newtouch.token", springmvc_token);
	            Constants.TOKEN_VALUE = token; //将token的值存入变量类,以便下次对比
	 
	        } catch (IllegalStateException e) {
	            logger.error("generateGUID() mothod find bug,by token session...");
	        }
	        return token;
	    }
	 
     //验证表单token值和session中的token值是否一致
    @SuppressWarnings("unchecked")
    public static boolean validToken(HttpServletRequest request) {
        String inputToken = getInputToken(request);//从请求中获取请求页面带来的token值
 
        if (inputToken == null) {
            logger.warn("token is not valid!inputToken is NULL");
            return false;
        }
 
        HttpSession session = request.getSession();
        Map<String, String> tokenMap = (Map<String, String>)session.getAttribute("newtouch.token");
        if (tokenMap == null || tokenMap.size() < 1) {
            logger.warn("token is not valid!sessionToken is NULL");
            return false;
        }
        //获取Session中保存的token值
        String sessionToken = tokenMap.get(Constants.DEFAULT_TOKEN_NAME);
         //将请求中获取的token值与Session中保存的token进行比较，如果相等表示是第一次请求，不相等则是重复的请求
        if (!inputToken.equals(sessionToken)) {
            logger.warn("token is not valid!inputToken='" + inputToken+ "',sessionToken = '" + sessionToken + "'");
            return false;
        }
        //当第一次请求的时候，移除Session中保存的token码，然后重新生成一个，放入Session
        session.removeAttribute(Constants.DEFAULT_TOKEN_NAME);
        Map<String,String> newtoken = new HashMap<String,String>();
        newtoken.put("newtouch.token",RandomStringUtils.randomAscii(36).toUpperCase());
        session.setAttribute("newtouch.token",newtoken);
 
        return true;
    }
    
    //获取表单中token值
    public static String getInputToken(HttpServletRequest request) {
     /*   @SuppressWarnings("rawtypes")
		Map params = request.getParameterMap();
 
        if (!params.containsKey(Constants.DEFAULT_TOKEN_NAME)) {
            logger.warn("Could not find token name in params.");
            return null;
        }
 
        String[] tokens = (String[]) params.get(Constants.DEFAULT_TOKEN_NAME);
         
        if ((tokens == null) || (tokens.length<1)) {
            logger.warn("Got a null or empty token name.");
            return null;
        }
    
        return tokens[0];
        */
        return   request.getParameter(Constants.DEFAULT_TOKEN_NAME);
    }
}
