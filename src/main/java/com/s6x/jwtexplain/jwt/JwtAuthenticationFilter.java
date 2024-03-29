package com.s6x.jwtexplain.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                final String token = this.getTokenFromRequest(request);
                if (token == null) {
                    filterChain.doFilter(request, response);
                    return;
                }
                filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String autHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(autHeader) &&  autHeader.startsWith("Bearer ")){
            return autHeader.substring(7);
        }
        return null;
    }
    
}
