package com.nisum.user.api.service;

import com.nisum.user.api.model.entity.User;

public interface TokenService {
	
	public String generateToken(User user);
	
	public void decodeToken(String token);

}
