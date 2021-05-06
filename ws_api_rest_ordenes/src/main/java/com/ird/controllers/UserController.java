package com.ird.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ird.DTO.LoginRequestDTO;
import com.ird.DTO.LoginResponseDTO;
import com.ird.DTO.SignupRequestDTO;
import com.ird.DTO.UserDTO;
import com.ird.converters.UserConverter;
import com.ird.entity.User;
import com.ird.services.UserService;
import com.ird.utils.WrapperResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter userConverter;

	@PostMapping(value = "/crearUsuario")
	public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request) {
		User user = userService.createUser(userConverter.signup(request));
		return new WrapperResponse<>(true, "Successful", userConverter.fromEntity(user))
				.createResponse();
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
		LoginResponseDTO responseDTO = userService.login(request);
		return new WrapperResponse<>(true, "Successful", responseDTO)
				.createResponse();
	}

}
