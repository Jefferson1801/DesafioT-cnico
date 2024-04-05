package com.prueba.demo.support.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
 

    @Value("${seguridad.secret.key}")
    public String secretKey;
    
    @Value("${seguridad.secret.jwtId}")
    public String tumiJWT;
    
    @Value("${seguridad.secret.prefix}")
    public String prefix;
    
    @Value("${seguridad.secret.time}")
    public Long time;    
    
    
	public String createToken(String username) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId(tumiJWT)
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + time))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return prefix+ " " + token;
	}
	
}
