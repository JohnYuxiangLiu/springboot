package com.appdeveloperblog.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appdeveloperblog.app.ws.shared.dto.UserDto;

//this interface is to convert input data and store in database, vice versa
public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);
}
