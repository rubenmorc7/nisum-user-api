package com.nisum.user.api.util;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nisum.user.api.exceptions.PatternDoesNotMatchException;
import com.nisum.user.api.model.dto.UserRegisterRequest;

public class UserValidationUtil {
	
	    
    private static final Logger log = LoggerFactory.getLogger(UserValidationUtil.class);
    
	public static void validateData(UserRegisterRequest newUser, String regexEmail, String regexPassword) {
		
		if( !patternMatches(newUser.getEmail(), regexEmail)) {
            log.error("El email no cumple con el formato requerido aaaaaaa@dominio.cl");
            throw new PatternDoesNotMatchException("El email no cumple con el formato requerido aaaaaaa@dominio.cl");
        }else if( !patternMatches(newUser.getPassword(), regexPassword)){
            log.error("El password no cumple con el formato requerido");
            throw new PatternDoesNotMatchException("El password no cumple con el formato requerido");
        }
		
	}
	
	public static boolean patternMatches(String string, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(string)
                .matches();
    }
	

}
