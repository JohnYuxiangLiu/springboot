package com.appdeveloperblog.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdeveloperblog.app.ws.UserRepository;
import com.appdeveloperblog.app.ws.io.entity.UserEntity;
import com.appdeveloperblog.app.ws.service.UserService;
import com.appdeveloperblog.app.ws.shared.dto.UserDto;

//this class is to do customised crud from UserService
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
//	input is an UserDto obj and output is still a UserDto, it's part of UserController createUser method
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		
		UserEntity userEntity=new UserEntity();
		
//		from user input to userDto, then userDto to userEntity, properties in userDto and userEntity must match to transfer/copy
		BeanUtils.copyProperties(user, userEntity);
		
//		because encryptedPassword and userId can't be null=false, so hard code here
		userEntity.setUserId("testUserId");
		userEntity.setEncryptedPassword("test");
		
//		use UserRepository to do crud and return a UserEntity obj
		UserEntity storedUserDetails=userRepository.save(userEntity);		
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

}
