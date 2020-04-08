package com.appdeveloperblog.app.ws.ui.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblog.app.ws.service.UserService;
import com.appdeveloperblog.app.ws.shared.dto.UserDto;
import com.appdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdeveloperblog.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users") //localhost:8080/users
public class UserController {

	@Autowired
//	autowired allows all methods from different classes which implemented UserService to be shown here
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
//		create new shared data class
		UserDto userDto=new UserDto();
//		create new response data class
		UserRest returnValue=new UserRest();
		
//		copy input properties userDetails to shared data class properties, non maching properites are ignored
		BeanUtils.copyProperties(userDetails, userDto);
		
//		userService is a man-made interface that is at service layer to store userDto to db, and then return an UserDto object for response to use
		UserDto createUser=userService.createUser(userDto);
		
		
//		transfer from createUser to returnValue
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
}
