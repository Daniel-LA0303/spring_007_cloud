package com.mx.mcsv.service.auth.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mx.mcsv.service.auth.dto.RequestDto;
import com.mx.mcsv.service.auth.entity.AuthUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	RouteValidator routeValidator;

	public String createToken(AuthUser authUser) {
		Map<String, Object> claims = new HashMap<>();
		claims = Jwts.claims().setSubject(authUser.getUserName());
		claims.put("id", authUser.getId());
		claims.put("role", authUser.getRole());
		Date now = new Date();
		Date exp = new Date(now.getTime() + 3600000);
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public String getUserNameFromToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			return "bad token";
		}
	}

	public boolean validate(String token, RequestDto dto) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}
		if (!isAdmin(token) && routeValidator.isAdminPath(dto)) {
			return false;
		}
		return true;
	}

	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}

	private boolean isAdmin(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role").equals("admin");
	}

}
