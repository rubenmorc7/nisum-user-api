package com.nisum.user.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.nisum.user.api.model.dto.UserRegisterRequest;
import com.nisum.user.api.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create-user")
    @Operation(summary="Crear un nuevo usuario", description ="Servicio para adicionar un nuevo usuario")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request format",
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject("{\n" +
                            "\"name\": \"Ruben Moreno\",\n" +
                            "\"email\": \"ruben@moreno.cl\",\n" +
                            "\"password\": \"Hawk123@\",\n" +
                            "\"phones\": [\n" +
                            "{\n" +
                            "\"number\": \"3333333\",\n" +
                            "\"citycode\": \"11\",\n" +
                            "\"countrycode\": \"57\"\n" +
                            "}\n" +
                            "]\n" +
                            "}\n")))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Registro insertado exitosamente",
                                    value = "")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Petición mal formada",
                                    value =  "")
                    )
            ),
            
    })
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest, BindingResult result){
    	return userService.createUser(userRegisterRequest);
    }

    @ApiResponses(value = {
		@ApiResponse(
                responseCode = "200",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(
                                name = "Registro encontrado",
                                value = "")
                )
        ),
		@ApiResponse(
                responseCode = "401",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(
                                name = "No se encuentra autorizado",
                                value =  "")
                )
        ),
    	@ApiResponse(
	            responseCode = "404",
	            content = @Content(
	                    mediaType = "application/json",
	                    examples = @ExampleObject(
	                            name = "Registro no encontrado",
	                            value =  "")
	            )
	    ),
    	
    })
    @GetMapping(value = "/findByEmail",produces = "application/json")
    @Operation(summary="Consultar usuario por email", description ="Servicio para consultar un usuario por email")
    public ResponseEntity<?> findByEmail(@RequestParam String token, 
    									 @RequestParam String email)
    {
        return userService.findByEmail(token, email);
    }
    
    
}
