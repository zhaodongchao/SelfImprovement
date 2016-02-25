package com.newtouch.securitys.login;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		// TODO Auto-generated method stub
		super.setDefaultTargetUrl(defaultTargetUrl);
	}

	@Override
	public void setAlwaysUseDefaultTargetUrl(boolean alwaysUseDefaultTargetUrl) {
		// TODO Auto-generated method stub
		super.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
	}



}
