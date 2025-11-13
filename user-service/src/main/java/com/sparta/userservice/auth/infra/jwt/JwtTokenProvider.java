package com.sparta.userservice.auth.infra.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	@Value("${jwt.prefix}")
	private String bearerPrefix;
	@Value("${jwt.access-token-expiration}")
	private Long accessTokenExpiration;
	@Value("${jwt.secret.key}")
	private String secretKey;
	private Key key;
	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	@PostConstruct
	public void init() {
		byte[] bytes = Base64.getDecoder().decode(secretKey);
		key = Keys.hmacShaKeyFor(bytes);
	}

	public TokenResponse generateToken(Long userId, String role) {
		String accessToken = removePrefix(createAccessToken(userId, role));
		return TokenResponse.from(accessToken);
	}

	public String createAccessToken(Long userId, String role) {

		Date date = new Date();

		return bearerPrefix +
			Jwts.builder()
				.setSubject(userId.toString())
				.claim("role", role)
				.setExpiration(new Date(date.getTime() + accessTokenExpiration))
				.setIssuedAt(date)
				.signWith(key, signatureAlgorithm)
				.compact();
	}

	public String removePrefix(String token) {
		return token.substring(bearerPrefix.length());
	}
}
