package com.nisum.user.api.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomResponseException {

    private String code;

    private String message;

}