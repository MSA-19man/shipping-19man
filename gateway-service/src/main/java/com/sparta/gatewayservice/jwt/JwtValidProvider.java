package com.sparta.gatewayservice.jwt;

import java.security.Key;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtValidProvider {

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

	public String resolveToken(ServerHttpRequest request) {
		String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (bearerToken != null && bearerToken.startsWith(bearerPrefix)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public Claims extractClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public Long getUserId(String token) {
		return extractClaims(token).get("userId", Long.class);
	}

	public String getRole(String token) {
		return extractClaims(token).get("role", String.class);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);

			return true;
		} catch (SecurityException | MalformedJwtException | SignatureException e) {
			log.warn("Invalid JWT signature, " + e.getMessage());
		} catch (ExpiredJwtException e) {
			log.warn("Expired JWT token, " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.warn("Unsupported JWT token, " + e.getMessage());
		} catch (IllegalArgumentException e) {
			log.warn("JWT claims is empty, " + e.getMessage());
		} catch (Exception e) {
			log.error("Error validating JWT token: " + e.getMessage());
		}
		return false;
	}
}
