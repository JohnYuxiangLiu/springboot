package com.appdeveloperblog.app.ws.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appdeveloperblog.app.ws.SpringApplicationContext;
import com.appdeveloperblog.app.ws.service.UserService;
import com.appdeveloperblog.app.ws.shared.dto.UserDto;
import com.appdeveloperblog.app.ws.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//auth username and password and send token to html
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

//	constructor
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

//	get req
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) {
		
		try {
			UserLoginRequestModel creds=new ObjectMapper().readValue(req.getInputStream(), UserLoginRequestModel.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword(),new ArrayList<>()));
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
//	send token
	@Override
	public void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth) throws IOException, ServletException {
//		return the identity of the principal who casted as User
		String userName=((User) auth.getPrincipal()).getUsername();
		
		String token=Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512,SecurityConstants.TOKEN_SECRET)
				.compact();
		
//		access UserServiceImpl to get userdata through getBean()
//		param is lower case starting
		UserService userService=(UserService) SpringApplicationContext.getBean("userServiceImpl");
		
//		get userservice data
		UserDto userDto=userService.getUser(userName);
		
		res.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+token);
		
//		return user data
		res.addHeader("UserID", userDto.getUserId());
	}
}
