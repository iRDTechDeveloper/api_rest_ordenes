package com.ird.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ird.DTO.LoginRequestDTO;
import com.ird.DTO.LoginResponseDTO;
import com.ird.converters.UserConverter;
import com.ird.entity.User;
import com.ird.exceptions.GeneralServiceException;
import com.ird.exceptions.NoDataFoundException;
import com.ird.exceptions.ValidateServiceException;
import com.ird.repository.UserRepository;
import com.ird.validator.UserValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Value("${jwt.password}")
	private String jwtSecret;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private PasswordEncoder pwsEncriptado;

	public User createUser(User user) {
		try {
			UserValidator.validarUser(user);
			
			User userExistente = userRepo.findByUsername(user.getUsername())
					.orElse(null);
			
			if (userExistente != null) throw new ValidateServiceException("El Nombre de Usuario Ya Existe...");
			
			String passEncoder = pwsEncriptado.encode(user.getPassword());
			user.setPassword(passEncoder);
			
			return userRepo.save(user);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public LoginResponseDTO login(LoginRequestDTO request) {
		try {
			User userLogin = userRepo.findByUsername(request.getUsername())
					.orElseThrow(() -> new ValidateServiceException("El Usuario o Password Es Invalidos..."));
			
			if(! pwsEncriptado.matches(request.getPassword(), userLogin.getPassword()))
				throw new ValidateServiceException("El Usuario o Password Es Invalidos...");
			
			//if (! userLogin.getPassword().equals(request.getPassword())) throw new ValidateServiceException("El Usuario o Password Es Invalidos...");
			
			String tokenLogin = createToken(userLogin);
			
			return new LoginResponseDTO(userConverter.fromEntity(userLogin), tokenLogin);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public String createToken(User user) {
		Date fechaInicioToken = new Date();
		Date fechaFinToken = new Date(fechaInicioToken.getTime() + (1000*60*20));
		
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(fechaInicioToken)
				.setExpiration(fechaFinToken)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public boolean validarToken(String tokenLogin) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(tokenLogin);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("El Formato del Token no coincide con el formato de autenticacion esperado por la aplicacion...");
		} catch (MalformedJwtException e) {
			log.error("El Token no se construyó correctamente, por favor genere un token correctamente...");
		} catch (SignatureException e) {
			log.error("No se pudo calcular una firma o verificar una firma existente del token...");
		} catch (ExpiredJwtException e) {
			log.error("El tiempo del token a vencido, debe renovar su tiempo de vigencia, favor generar un nuevo token...");
		}
		return false;
	}

	public String getUsernameFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidateServiceException("Token Invalido...");
		}
	}
}
