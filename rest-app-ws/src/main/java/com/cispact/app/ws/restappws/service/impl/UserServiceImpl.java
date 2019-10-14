package com.cispact.app.ws.restappws.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cispact.app.ws.repository.UserRepository;
import com.cispact.app.ws.restappws.entity.UserEntity;
import com.cispact.app.ws.restappws.service.UserService;
import com.cispact.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    BCryptPasswordEncoder encoder;

	@Override
	public UserDto createUser(UserDto user) {
		
		if(userRepository.findUserByEmail(user.getEmail())!=null) {
			throw new RuntimeException("User already exists!");
		}
		
		UserEntity userEntity =new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(encoder.encode(user.getPassword()));
		  
		String generatedUserId=UUID.randomUUID().toString().substring(0, 10).toUpperCase();
		userEntity.setUserId(generatedUserId);
		
		UserEntity stroredUserDetails=userRepository.save(userEntity);
		
		UserDto returnValue =new UserDto();
		BeanUtils.copyProperties(stroredUserDetails,returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
