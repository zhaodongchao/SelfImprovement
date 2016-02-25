package com.newtouch.securitys;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.newtouch.entitys.Role;
import com.newtouch.entitys.User;
import com.newtouch.services.IUserService;
/**
 *   密码验证并授权, 成功则返回一个用户
 * @author 赵东朝
 * 2015年12月7日
 */
@Repository("customUserDetailsService")
public class CustomUserDetailsService  implements UserDetailsService {
   
	@Autowired
	private IUserService userServiceImpl ;
	
	/**
	 * the username identifying the user whose data is required
	 * @loginUserName 要登陆者的姓名
	 */
	@Override
	public UserDetails loadUserByUsername(String loginUserName) throws UsernameNotFoundException {
        //根据loginUserName到数据库中查询这个用户
		User luser = new User();
		luser.setUsername(loginUserName);
		User user = userServiceImpl.findByCondition(luser).get(0);
		Collection<? extends GrantedAuthority> authorities = this.obtionGrantedAuthorities(user);
		user.setAuthorities(authorities);
		
		return user;
	}
   
	
	// 取得用户的权限,
	@SuppressWarnings("deprecation")
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		//获取所有的角色Rolecode
		for(Role role :roles){
			authSet.add(new GrantedAuthorityImpl(role.getRoleCode())) ;
		}
		
		return authSet ;
	}
}
