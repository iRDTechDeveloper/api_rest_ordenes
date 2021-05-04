package com.ird.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ird.entity.User;
import com.ird.exceptions.GeneralServiceException;
import com.ird.exceptions.NoDataFoundException;
import com.ird.exceptions.ValidateServiceException;
import com.ird.repository.UserRepository;
import com.ird.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User createUser(User user) {
		try {
			UserValidator.validarUser(user);
			
			User userExistente = userRepo.findByUsername(user.getUsername())
					.orElse(null);
			
			if (userExistente != null) throw new ValidateServiceException("El Nombre de Usuario Ya Existe...");
			
			return userRepo.save(user);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
