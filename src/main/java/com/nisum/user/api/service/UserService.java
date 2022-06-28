package com.nisum.user.api.service;

import org.springframework.http.ResponseEntity;

import com.nisum.user.api.model.dto.UserRegisterRequest;

public interface UserService {
	
	public ResponseEntity<?> createUser(UserRegisterRequest newUser); 

    public ResponseEntity<?> findByEmail(String token, String email);
        
}
