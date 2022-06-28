package com.nisum.user.api.service.impl;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nisum.user.api.exceptions.UnprocesableEntityException;
import com.nisum.user.api.exceptions.UserNotFoundException;
import com.nisum.user.api.model.dto.UserRegisterRequest;
import com.nisum.user.api.model.entity.User;
import com.nisum.user.api.repositories.UserRepository;
import com.nisum.user.api.service.TokenService;
import com.nisum.user.api.service.UserService;
import com.nisum.user.api.util.UserMapper;
import com.nisum.user.api.util.UserValidationUtil;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService; 
    
    @Autowired
	private Environment env;
	
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Override
    public ResponseEntity<?> createUser(UserRegisterRequest userRegisterRequest) {
    	String regexEmail = env.getProperty("regex.user.email");
    	String regexPassword = env.getProperty("regex.user.password");
    	if(userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()){
            log.error("El usuario ya existe para el email ingresado" + userRegisterRequest.getEmail());
            throw new UnprocesableEntityException("El usuario ya existe para el email ingresado");
        }    	
    	new UserValidationUtil();    	
		UserValidationUtil.validateData(userRegisterRequest, regexEmail, regexPassword);
        new UserMapper();		
		User user = UserMapper.buildUserEntity(userRegisterRequest);
        user.setToken(tokenService.generateToken(user));
        try {
            user = userRepository.save(user);
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new UnprocesableEntityException("No es posible registrar el usuario");
        }
        new UserMapper();
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }
    
    @Override
    public ResponseEntity<?> findByEmail(String token, String email) {
        tokenService.decodeToken(token);        
    	Optional<User> user = userRepository.findByEmail(email);
    	if(!user.isPresent()){
    		log.error("Usuario no existe para el mail ingresado");
            throw new UserNotFoundException("Usuario no existe para el mail ingresado");
        }
    	return ResponseEntity.status(HttpStatus.OK).body(user);        
    }
}
