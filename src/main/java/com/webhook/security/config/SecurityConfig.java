package com.webhook.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	@Configuration
	@Order(2)
	public static class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("pass123")).roles("USER");
			
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().antMatchers("/api/noauth/**").permitAll()
			.antMatchers("/api/basic/**").authenticated().and().httpBasic();
			
		}
		
		@Bean
		public PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
	}
	
	@Configuration
	@Order(1)
	public static class TokenSecurityConfig extends WebSecurityConfigurerAdapter{
		
		private static final String authHeaderValue = "Bearer marketer";		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			TokenSecurityFilter filter = new TokenSecurityFilter();
			
			filter.setAuthenticationManager(new AuthenticationManager() {
				
				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					
					String principal = (String) authentication.getPrincipal();
					
					if (!authHeaderValue.equals(principal))
	                {
	                    throw new BadCredentialsException("The API key was not found "
	                                                + "or not the expected value.");
	                }
					
					authentication.setAuthenticated(true);
	                return authentication;
					
				}
			});
			
			http.antMatcher("/api/token/**").csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(filter)
			.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()), filter.getClass())
			.authorizeRequests().anyRequest().authenticated();
			
		}
		
	}
	

}
