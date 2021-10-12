package com.denil.jwt.api.controller;

import com.denil.jwt.api.entity.AuthRequest;
import com.denil.jwt.api.entity.User;
import com.denil.jwt.api.repository.UserRepository;
import com.denil.jwt.api.service.CustomUserDetailsService;
import com.denil.jwt.api.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin

public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService us;
    @Autowired
    UserRepository ur;
    @GetMapping("/")
    
    public ResponseEntity<Object> welcome(@RequestHeader(value="Authorization") String authorizationHeader) {
    	
    	System.out.println("from cors hit get method");
    	 String token = authorizationHeader.substring(7);
    	String userName = jwtUtil.extractUsername(token);
    	System.out.println("from / spring security");
    	 UserDetails userDetails = us.loadUserByUsername(userName);
    	  User u1=us.findByUserNameAndPassword(userDetails.getUsername(),  userDetails.getPassword());
        return new ResponseEntity<>(u1.getRole(),HttpStatus.OK);
    }

    
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
        	System.out.println("from cors hit controller");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
            
        } catch (Exception ex) {
           return ("invalid username/password");
        }
        User u1=us.findByUserNameAndPassword(authRequest.getUserName(),  authRequest.getPassword());
        return u1.getRole()+" "+u1.getId()+" "+jwtUtil.generateToken(authRequest.getUserName());
    }
    
    @GetMapping("/userdetails")
    
    public String loggedInUserDetails(@RequestHeader(value="Authorization") String authorizationHeader) {
    	
    	System.out.println("from cors hit get method");
    	 String token = authorizationHeader.substring(7);
    	String userName = jwtUtil.extractUsername(token);
    	System.out.println("from / spring security");
    	 UserDetails userDetails = us.loadUserByUsername(userName);
    	  User u1=us.findByUserNameAndPassword(userDetails.getUsername(),  userDetails.getPassword());
        return (u1.getId()+","+u1.getUserName()+","+u1.getRole());
    }
    
    @PostMapping("/signup")
    public String signup(@RequestBody User u1 ) {
    	ur.save(u1);
    	// create an instance of RestTemplate
    	RestTemplate restTemplate = new RestTemplate();

    	// create headers
    	HttpHeaders headers = new HttpHeaders();
    	// set `content-type` header
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	// request body parameters
    	Map<String, Object> map = new HashMap();
    	map.put("userId", 1);
    	map.put("title", "Spring Boot 101");
    	map.put("body", "A powerful tool for building web apps.");
    	return "Signed Up";
    }

    
    
}
