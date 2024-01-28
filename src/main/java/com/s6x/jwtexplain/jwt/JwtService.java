package com.s6x.jwtexplain.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {
	//@Autowired
	//private static final String SECRET_KEY="CULO_TETAS_AVION";

	public String getToken(UserDetails user) {
		return getToken(new HashMap<>(), user);
	}

	private String getToken(Map<String, Object> extraClaim, UserDetails user) {
		// TODO Auto-generated method stub
		return Jwts
				.builder()
				.setClaims(extraClaim)
				.setSubject(user.getUsername())
				.setSubject(user.getPassword())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getKey(), SignatureAlgorithm.HS512)
				.compact();
	}
	
	private Key getKey() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}

}
