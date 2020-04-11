package com.appdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appdeveloperblog.app.ws.io.entity.UserEntity;
import com.appdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appdeveloperblog.app.ws.service.UserService;
import com.appdeveloperblog.app.ws.shared.Utils;
import com.appdeveloperblog.app.ws.shared.dto.UserDto;

//this class is to do customised crud from UserService
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
//	input is an UserDto obj and output is still a UserDto, it's part of UserController createUser method
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		
		UserEntity userEntity=new UserEntity();
				
//		xtra logic: if email already in db, return exception
		if(userRepository.findByEmail(user.getEmail())!=null) {throw new RuntimeException("Record already exists");}
		
//		from user input to userDto, then userDto to userEntity, properties in userDto and userEntity must match to transfer/copy
		BeanUtils.copyProperties(user, userEntity);
		
//		generate 30 chars random user id
		String publicUserId=utils.generateUserId(30);
				
//		because encryptedPassword and userId can't be null=false, so hard code here
		userEntity.setUserId(publicUserId);
//		convert password to encrypted password
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
//		use UserRepository to do crud and return a UserEntity obj
		UserEntity storedUserDetails=userRepository.save(userEntity);		
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}
	
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity=userRepository.findByEmail(email);
		
		if(userEntity==null) throw new UsernameNotFoundException(email);
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		UserServiceImpl impl UserService which impl UserServiceDetails that has a method loadUserByUsername, so you must impl that method here, auto-gen
		UserEntity userEntity=userRepository.findByEmail(email);

//		it's not going to userDto nor userRequest model
		if(userEntity==null) throw new UsernameNotFoundException(email);

//		because the User takes a 3rd param that is a collection
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

}
