package com.appdeveloperblog.app.ws.service;

import com.appdeveloperblog.app.ws.shared.dto.UserDto;

//this interface is to convert input data and store in database, vice versa
public interface UserService {
	UserDto createUser(UserDto userDto);
}
