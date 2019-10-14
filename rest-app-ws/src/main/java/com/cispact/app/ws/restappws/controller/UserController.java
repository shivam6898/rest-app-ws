package com.cispact.app.ws.restappws.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cispact.app.ws.restappws.model.request.UserDetails;
import com.cispact.app.ws.restappws.model.response.UserRest;
import com.cispact.app.ws.restappws.service.UserService;
import com.cispact.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "from get user";
	}
	
      //create user
	@PostMapping
	public UserRest createUser(@RequestBody UserDetails userdetails) {
	     UserRest returnValue =new UserRest(); 
		 UserDto userdto=new UserDto();
		 //get the user details from userdetail and copy to userdto
		 BeanUtils.copyProperties(userdetails, userdto);
		UserDto createdUser =userService.createUser(userdto);
		 BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "from put user";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "from delete user";
	}
	
}
