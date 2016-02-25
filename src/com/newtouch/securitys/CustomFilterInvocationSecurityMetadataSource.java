package com.newtouch.securitys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Repository;

import com.newtouch.entitys.Permission;
import com.newtouch.entitys.Role;
import com.newtouch.services.IRoleService;
/**
 * 这个类实现FilterInvocationSecurityMetadataSource，
 * 这个接口是springsecurity指定的用于在服务器启动时用于加载系统资源与权限列表的接口
 * 
 * 
 * 核心的InterceptorStatusToken token = super.beforeInvocation(fi);会调用我们定义的accessDecisionManager:decide(Object object)和securityMetadataSource
 *
 * :getAttributes(Object object)方法。
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * 
 * 
 * @author 赵东朝
 * 2015年12月6日
 */
@Repository("customFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
  
	//key:url, value:角色名(ROLE_**) ，ConfigAttribute是SpringSecurity专门提供用于存放角色的一种变量类型，其实与String类似，里面就是存放一个String（角色名）
	private HashMap<String,Collection<ConfigAttribute>> resourceMap = null ;
	
	@Autowired
	private IRoleService roleServiceImpl ;
	
	/**
	 * 自定义方法，这个类放入到Spring容器后，web容器初始化完成后，就会执行 指定的init方法，从数据库中读取资源，放入map里面
	 *  Only one method can be annotated with this annotation
	 */
	@PostConstruct
	public void init(){
		
		loadResourceDefine();
	}
	private void loadResourceDefine() {
		
		/*
		 * 应当是资源url为key， 角色名称为value。角色名称就是那些以ROLE_为前缀的值
		 */
		resourceMap = new HashMap<String,Collection<ConfigAttribute>>();
		
		List<Role> roleList = roleServiceImpl.ListRoles(new Role());
		for(Role role:roleList){
			ConfigAttribute configAttrbute = new SecurityConfig(role.getRoleCode());//将角色（ROLE_**）放入configAttrbute里面 ，configAttrbute实际上是String的一个子类
			
			Set<Permission> permissions = role.getPermissions();//获取这个角色对应的权限集合
			
			/*
			 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
			 * 
			 *  index.jsp  ROLE_AdMIN 
			 *  index.jsp  ROLE_MAN 
			 * map<index.jsp, [ROLE_AdMIN,ROLE_MAN]>
			 * 
			 */
			for(Permission p:permissions){
				String uri = p.getPermission_uri();//获取资源的uri
				
				if(resourceMap.containsKey(uri)){
					Collection<ConfigAttribute> collects = resourceMap.get(uri);
					collects.add(configAttrbute);
					resourceMap.put(uri, collects);
				}else{
					Collection<ConfigAttribute> newCollects = new ArrayList<>();
					newCollects.add(configAttrbute);
					resourceMap.put(uri, newCollects);
				}
				
			}
			
		}
		System.out.println(resourceMap);
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
     
	/**
	 * 根据用户访问的uri，加载该uri所需要角色列表　
	 * Object object:uri地址
	 * 
	 * @param object the object being secured
     *
     * @return the attributes that apply to the passed in secured object. Should return an empty collection if there
     *         are no applicable attributes.
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	  
       //最初请求的uri格式:/**/index.jsp
		// object 是一个URL ,为用户请求URL
		String url = ((FilterInvocation) object).getRequestUrl().trim();
		
		if ("/".equals(url)) {
			return null;
		}
		
		int firstQuestionMarkIndex = url.indexOf(".");
		// 判断请求是否带有参数 如果有参数就去掉后面的后缀和参数(/index.do --> /index)
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		//最终形成的uri 格式:/index
		

		Iterator<String> iterator = resourceMap.keySet().iterator();
		// 取到请求的URL后与上面取出来的资源做比较
		while (iterator.hasNext()) {
			String resURL = iterator.next().trim();
			if (url.equals(resURL)) {
				//获得该uri所需要的角色列表
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
