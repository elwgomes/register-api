package com.elwgomes.cadastro.api.security.config;

import com.elwgomes.cadastro.api.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

	private static final String EMISSOR = "Leonardo";
	private static final String TOKEN_HEADER = "Bearer ";
	private static final String TOKEN_KEY = "0123456789091234567890912345678901";

	public static AuthToken encodeToken (UserDTO user) {
		Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
		String tokenJWT = Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuer(EMISSOR)
				.setExpiration(new Date(System.currentTimeMillis() + 60000 * 10)) // 10 minutes token
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();

		AuthToken token = new AuthToken(TOKEN_HEADER + tokenJWT);

		return token;
	}

    public static Authentication decodeToken (HttpServletRequest request) {
		try {
			String jwtToken = request.getHeader("Authorization");

		jwtToken = jwtToken.replace(TOKEN_HEADER, "");

		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(TOKEN_KEY.getBytes()).build().parseClaimsJws(jwtToken);

		String user = jwsClaims.getBody().getSubject();
		String emissor = jwsClaims.getBody().getIssuer();
		Date validate = jwsClaims.getBody().getExpiration();

		if (user.length() > 0
				&& emissor.equals(EMISSOR)
				&& validate.after(new Date(System.currentTimeMillis()))) {
	    return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	return null;
    }	
}

