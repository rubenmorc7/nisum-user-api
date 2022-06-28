package com.nisum.user.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nisum.user.api.model.dto.UserRegisterRequest;
import com.nisum.user.api.model.dto.UserRegisterResponse;
import com.nisum.user.api.model.entity.Phone;
import com.nisum.user.api.model.entity.User;


public class UserMapper {
	
	public static User buildUserEntity(UserRegisterRequest request) {
        List<Phone> phoneList = request.getPhones().stream()
                .map(phone-> Phone.builder()
                        .number(phone.getNumber())
                        .cityCode(phone.getCityCode())
                        .countryCode(phone.getCountryCode())
                        .build())
                .collect(Collectors.toList());
        		
        User user = User
                .builder()                
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .created(LocalDate.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)                
                .phones(phoneList)
                .build();
        
        user.getPhones().forEach(phone ->  phone.setUser(user));
        
        return user;
    }
	
	public static UserRegisterResponse toResponse(User user){

        return UserRegisterResponse.builder()
                .id(user.getUuid().toString())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.getIsActive())
                .build();
    }

    
}
