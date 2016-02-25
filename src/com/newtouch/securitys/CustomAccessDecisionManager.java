package com.newtouch.securitys;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
/**
 * 匹配用户拥有权限和请求权限
 * @author 赵东朝
 * 2015年12月7日
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

	

	/**
	 * 思路:如果该页面不需要权限访问,则直接结束
	 * authentication:用户的权限
	 * configAttributes:访问该资源所需要的权限
	 */
	@Override
	public void decide(Authentication authentication, Object obj, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		  
		
		if (null == configAttributes) {
			return;
		}
		for(ConfigAttribute configAttribute : configAttributes){//遍历访问该自然所需要的权限及ROLE_**
			String needRole = configAttribute.getAttribute();
			
			Collection<? extends GrantedAuthority> currAuthority = authentication.getAuthorities() ;//获取当前用户的所有权限
	        
			for(GrantedAuthority grantedAuthority:currAuthority){
				
			/*	if("ROLE_ANONYMOUS".equals(grantedAuthority.getAuthority())){
					return ;
				}*/
				//如果访问该资源的角色中有一个与该用户的角色相符合的，就放行，否则抛出一个异常
				if(needRole.trim().equals(grantedAuthority.getAuthority())){
					return ;
				  }
			  }
		   }
    
		        //该用户没有权限访问该资源
				throw new AccessDeniedException("Access Denied");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
