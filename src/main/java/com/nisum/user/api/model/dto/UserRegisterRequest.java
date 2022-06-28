package com.nisum.user.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.user.api.model.entity.Phone;
import com.nisum.user.api.model.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

	@NotNull(message = "El nombre es obligatorio")
	@NotEmpty(message = "El nombre no puede estar vacio")
	@NotBlank(message = "El nombre no puede estar en blanco")
	@JsonProperty("name")		
    private String name;
	
	@NotNull(message = "El email es obligatorio")
	@NotEmpty(message = "El email no puede estar vacio")
	@NotBlank(message = "El email no puede estar en blanco")
	@JsonProperty("email")
    private String email;
	
	@NotNull(message = "El password es obligatorio")
	@NotEmpty(message = "El password no puede estar vacio")
	@NotBlank(message = "El password no puede estar en blanco")
	@JsonProperty("password")
    private String password;    
	private List<UserPhoneRequest> phones;
    
}
