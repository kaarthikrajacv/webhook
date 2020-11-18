package com.webhook.security.config;


import javax.servlet.http.HttpServletRequest;



import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


public class TokenSecurityFilter extends AbstractPreAuthenticatedProcessingFilter{
	
	private static final String authHeaderName = "Authorization";

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return request.getHeader(authHeaderName);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		
		return "N/A";
	}

	

}
