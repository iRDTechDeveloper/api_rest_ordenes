package com.ird.validator;

import com.ird.entity.User;
import com.ird.exceptions.ValidateServiceException;

public class UserValidator {
	
	public static void validarUser(User user) {
		if(user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			throw new ValidateServiceException("EL nombre de Usuario es requerido!!! ...");
		}
		
		if (user.getUsername().length() > 50 ) {
			throw new ValidateServiceException("EL nombre es incorrecto, (Min 3 Digitos)-(Max 50 Digitos)!!! ...");
		}
		
		if(user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new ValidateServiceException("EL password es requerido!!! ...");
		}
		
		if (user.getPassword().length() > 50 ) {
			throw new ValidateServiceException("EL password es incorrecto, (Min 5 Digitos)-(Max 50 Digitos)!!! ...");
		}
	}

}
