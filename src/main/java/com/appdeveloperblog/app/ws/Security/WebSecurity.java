package com.appdeveloperblog.app.ws.Security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appdeveloperblog.app.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

//	UserDetails is manmade extends UserDetailsService interface
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		
	}
	
//	disable/permitAll WebSecurity dependencies to allow only post method to pass to "/users" without security password
//	anyRequest() means any other requests will need to be authenticated, aka protected
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().
		disable().authorizeRequests().antMatchers(HttpMethod.POST,"/users").permitAll()
		.anyRequest().authenticated();
	}
	
//	userDetailsService allows customise authentication, and this method is to encrypt the user input password
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
