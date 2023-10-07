package com.examportal.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.examportal.Service.impl.UserDetailServiceImp;


@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailServiceImp userDetailServiceImp;


    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
                final String requestTokenHeader=request.getHeader("Authorization");
                System.out.println("///////            "+requestTokenHeader);
                String username=null;
                String jwtToken=null;


                if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){

                    jwtToken=requestTokenHeader.substring(7);
                    try{
                   username= this.jwtUtils.extractUsername(jwtToken);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("jwt token not start with Bear");
                }

                // validate the token.
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    // final UserDetails userDetails=this.use

                    final UserDetails userDetails=this.userDetailServiceImp.loadUserByUsername(username);
                    if(this.jwtUtils.validateToken(jwtToken, userDetails)){
                       
                       UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken=
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                       usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    }
                }
                else{

                    System.out.println("token is not valid");
                }

               filterChain.doFilter(request, response);


    }
    
}
