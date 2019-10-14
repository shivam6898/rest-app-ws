package com.cispact.app.ws.restappws.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.cispact.app.ws.shared.dto.UserDto;


public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto user);


}
