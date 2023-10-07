package com.examportal.controller;

import java.security.Principal;

import javax.persistence.CacheStoreMode;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.Service.impl.UserDetailServiceImp;
import com.examportal.config.JwtUtils;
import com.examportal.model.JwtRequest;
import com.examportal.model.JwtResponse;
import com.examportal.model.User;

@RestController()
@CrossOrigin("*")
public class AuthenciateController {

    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;


    @Autowired
    private JwtUtils jwtUtils;
    
// generate token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
            try{
                authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
            }
            catch(UsernameNotFoundException e){
                e.printStackTrace();
                throw new Exception("User not found");
            }
            UserDetails userDetails=this.userDetailServiceImp.loadUserByUsername(jwtRequest.getUsername());
            
            System.out.println("After User Service..................."+userDetails);
  
           String token= this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));


    }

    private void authenticate(String username, String password) throws Exception{

    	System.out.println(username+" : "+password);
    	
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch( DisabledException e){
            throw new Exception("USER IS DIABLE"+e.getMessage());
        }
        catch(BadCredentialsException e){
            throw new Exception("INVALID CREDITIONAL"+e.getMessage());
 
        }



    }
    
    
    	@GetMapping("/current-user")
    	public User getCurrentUser( Principal principal) {
    		
    		return ((User)this.userDetailServiceImp.loadUserByUsername(principal.getName()));
    		
    	}
    

}
