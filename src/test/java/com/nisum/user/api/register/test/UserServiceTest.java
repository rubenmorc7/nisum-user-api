package com.nisum.user.api.register.test;

import com.nisum.user.api.model.dto.UserPhoneRequest;
import com.nisum.user.api.model.dto.UserRegisterRequest;
import com.nisum.user.api.model.entity.User;
import com.nisum.user.api.repositories.UserRepository;
import com.nisum.user.api.service.TokenService;
import com.nisum.user.api.service.UserService;
import com.nisum.user.api.util.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@TestPropertySource(properties = { "regex.user.password=^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" })
@TestPropertySource(properties = { "regex.user.email=^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" })
public class UserServiceTest {

    @Autowired
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;
    
    @Autowired
    TokenService tokenService;

    @Test
    public void registerUser_Test_CREATED(){

        UserPhoneRequest userPhoneRequest = UserPhoneRequest.builder()
                .number("123456")
                .cityCode("1")
                .countryCode("5")        
                .build();
        List<UserPhoneRequest> phoneRequests= new ArrayList<UserPhoneRequest>();
        phoneRequests.add(userPhoneRequest);
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .email("abc@email.com")
                .name("name")
                .password("password")
                .phones(phoneRequests)
                .build();

        UUID uuid = UUID.randomUUID();
        
        new UserMapper();
		User user = UserMapper.buildUserEntity(userRegisterRequest);

        User savedUser = user;

        savedUser.setUuid(uuid);

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiaHR0cDovL25pc3VtLmNvbSIsIm5hbWUiOiJKdWFuIFJvZHJpZ3VleiIsImVtYWlsIjoianVhbkByb2RyaWd1ZXoub3JnIiwiaWF0IjoxNDY2Nzk2ODIyLCJleHAiOjQ2MjI0NzA0MjJ9.mX_xp5sN1b1Cw8khS0t6TTxO0vCP_hdhz4TbrZUvjzY";

        TokenService tokenService = Mockito.mock(TokenService.class);
        when(tokenService.generateToken(user)).thenReturn(token);

        user.setToken(token);
        savedUser.setToken(token);

        when(userRepository.save(Mockito.any())).thenReturn(savedUser);
        
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(savedUser));

        Assert.assertEquals( responseEntity, userService.createUser(userRegisterRequest));

    }

    @Test
    public void registerNewUser_Email_NOTVALID(){

        UserPhoneRequest userPhoneRequest = UserPhoneRequest.builder()
                .number("123456")
                .cityCode("1")
                .countryCode("5")
                .build();
        
        List<UserPhoneRequest> phoneRequests= new ArrayList<UserPhoneRequest>();
        phoneRequests.add(userPhoneRequest);

        UserRegisterRequest newUserRequest = UserRegisterRequest.builder()
                .email("abcemail.com")
                .name("name")
                .password("1234")
                .phones(phoneRequests)
                .build();
        userService.createUser(newUserRequest);
    }

    @Test
    public void registerUser_Password_NOTVALID(){

        UserPhoneRequest userPhoneRequest = UserPhoneRequest.builder()
                .number("123456")
                .cityCode("1")
                .countryCode("5")
                .build();
        List<UserPhoneRequest> phoneRequests= new ArrayList<UserPhoneRequest>();
        phoneRequests.add(userPhoneRequest);
        UserRegisterRequest newUserRequest = UserRegisterRequest.builder()
                .email("abc@email.com")
                .name("name")
                .password("|132146576897568\ts\tS")
                .phones(phoneRequests)
                .build();

        userService.createUser(newUserRequest);
    }

    @Test
    public void registerUser_AlreadyExist(){

        UserPhoneRequest userPhoneRequest = UserPhoneRequest.builder()
                .number("123456")
                .cityCode("1")
                .countryCode("5")
                .build();
        List<UserPhoneRequest> phonesRequest= new ArrayList<UserPhoneRequest>();
        phonesRequest.add(userPhoneRequest);
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .email("abcemail.com")
                .name("name")
                .password("1234")
                .phones(phonesRequest)
                .build();
        
        new UserMapper();
		User user = UserMapper.buildUserEntity(userRegisterRequest);

        Optional<User> usera = Optional.ofNullable(user);

        Mockito.when(userRepository.findByEmail(userRegisterRequest.getEmail())).thenReturn(usera);

        userService.createUser(userRegisterRequest);
    }


}
