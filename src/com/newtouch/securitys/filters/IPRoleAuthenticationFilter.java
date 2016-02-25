package com.newtouch.securitys.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Ip过滤器，可以决定哪些角色权限可以访问 哪些IP
 * 将这个过滤器的位置放在FilterSecurityInterceptor之前，因为在此过滤器之前，用户的身份信息就确定了
 * 实现需要在springsecurity的命名空间添加
 * <custom-filter ref="ipFilter" before="FILTER_SECURITY_INTERCEPTOR"/>
 * 
 * @author 赵东朝 2015年12月25日
 */
public class IPRoleAuthenticationFilter extends OncePerRequestFilter {
	private String targetRole;
	private List<String> allowedIPAddresses;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// before we allow the request to proceed, we'll first get the user's
		// role
		// and see if it's an administrator
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && targetRole != null) {
			boolean shouldCheck = false;
			// look if the user is the target role
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				if (authority.getAuthority().equals(targetRole)) {
					shouldCheck = true;
					break;
				}
			}
			// if we should check IP, then check
			if (shouldCheck && allowedIPAddresses.size() > 0) {
				boolean shouldAllow = false;
				for (String ipAddress : allowedIPAddresses) {
					if (request.getRemoteAddr().equals(ipAddress)) {
						shouldAllow = true;
						break;
					}
				}
				if (!shouldAllow) {
					// fail the request
					throw new AccessDeniedException(
							"Access has been denied for your IP address: " + request.getRemoteAddr());
				}
			}
		} else {
			logger.warn(
					"The IPRoleAuthenticationFilter should be placed after the user has been authenticated in the filter chain.");
		}
		filterChain.doFilter(request, response);
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	public List<String> getAllowedIPAddresses() {
		return allowedIPAddresses;
	}

	public void setAllowedIPAddresses(List<String> allowedIPAddresses) {
		this.allowedIPAddresses = allowedIPAddresses;
	}

}
