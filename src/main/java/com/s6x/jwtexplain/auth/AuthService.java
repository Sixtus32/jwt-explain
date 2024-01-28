package com.s6x.jwtexplain.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.s6x.jwtexplain.jwt.JwtService;
import com.s6x.jwtexplain.repository.UserRepository;
import com.s6x.jwtexplain.user.Role;
import com.s6x.jwtexplain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final JwtService jwtService;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
    public AuthResponse login(LoginRequest request){
    	this.authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    	UserDetails user = this.userRepository.findByUsername(request.getUsername()).orElseThrow();
    	String token = this.jwtService.getToken(user);
    	return AuthResponse.builder()
    			.token(token)
    			.build();
    }
    
    public AuthResponse register(RegisterRequest request){
    	User user = User.builder()
    			.username(request.getUsername())
    			.password(request.getPassword())
    			.firstname(request.getFirstname())
    			.lastname(request.getLastname())
    			.country(request.getCountry())
    			.role(Role.USER)
    			.build();
    	this.userRepository.save(user);
        return AuthResponse.builder()
        		.token(jwtService.getToken(user))
        		.build();
    }
} 
