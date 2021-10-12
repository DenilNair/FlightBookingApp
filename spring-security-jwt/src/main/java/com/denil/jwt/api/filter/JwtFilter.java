package com.denil.jwt.api.filter;

import com.denil.jwt.api.service.CustomUserDetailsService;
import com.denil.jwt.api.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
try {      String authorizationHeader = httpServletRequest.getHeader("Authorization");

String token = null;
String userName = null;
String loginOrSignup="signup";
if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    token = authorizationHeader.substring(7);
    loginOrSignup="login";
    userName = jwtUtil.extractUsername(token);
}

if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
if(!userName.equals("Token error")) {
	
	UserDetails userDetails = service.loadUserByUsername(userName);

if (jwtUtil.validateToken(token, userDetails)) {

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
}
}
   
}

	if((loginOrSignup.equals("login"))) {
		if(userName==null) {
			httpServletResponse.addHeader("Exception caught", "invalid token");
			httpServletResponse.getWriter().write("Invalid Token");
		}
		else if (userName.equals("Token error")) {
			httpServletResponse.addHeader("Exception caught", "invalid token");
			httpServletResponse.getWriter().write("Invalid Token");	
		}
		else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		}
else {
	filterChain.doFilter(httpServletRequest, httpServletResponse);
}
}
catch(Exception e ) {
	System.out.println("Exception caught"+e);}
  
    }
}
