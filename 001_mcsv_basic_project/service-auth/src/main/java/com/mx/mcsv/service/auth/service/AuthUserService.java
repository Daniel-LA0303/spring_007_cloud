package com.mx.mcsv.service.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.mcsv.service.auth.dto.AuthUserDto;
import com.mx.mcsv.service.auth.dto.NewUserDto;
import com.mx.mcsv.service.auth.dto.RequestDto;
import com.mx.mcsv.service.auth.dto.TokenDto;
import com.mx.mcsv.service.auth.entity.AuthUser;
import com.mx.mcsv.service.auth.repository.AuthUserRepository;
import com.mx.mcsv.service.auth.security.JwtProvider;

@Service
public class AuthUserService {

	@Autowired
	AuthUserRepository authUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtProvider jwtProvider;

	public TokenDto login(AuthUserDto dto) {
		Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
		if (!user.isPresent()) {
			return null;
		}
		if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
			return new TokenDto(jwtProvider.createToken(user.get()));
		}
		return null;
	}

	public AuthUser save(NewUserDto dto) {
		Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
		if (user.isPresent()) {
			return null;
		}
		String password = passwordEncoder.encode(dto.getPassword());
		AuthUser authUser = AuthUser.builder().userName(dto.getUserName()).password(password).role(dto.getRole())
				.build();
		return authUserRepository.save(authUser);
	}

	public TokenDto validate(String token, RequestDto dto) {
		if (!jwtProvider.validate(token, dto)) {
			return null;
		}
		String username = jwtProvider.getUserNameFromToken(token);
		if (!authUserRepository.findByUserName(username).isPresent()) {
			return null;
		}
		return new TokenDto(token);
	}
}
