package com.nisum.user.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.user.api.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterResponse {

    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modified;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    private String token;

    private Boolean isActive;
    
}
