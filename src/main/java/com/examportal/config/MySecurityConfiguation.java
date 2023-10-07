package com.examportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.examportal.Service.impl.UserDetailServiceImp;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class MySecurityConfiguation extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthicateEntryPoint unauthoarizehandler;


	@Autowired
	private UserDetailServiceImp userDetailServiceImp;
	
	@Autowired
	private JwtAuthenticateFilter jwtAuthenticateFilter;
	 
      @Override
	  @Bean
	  public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	  }

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		auth.userDetailsService(this.userDetailServiceImp).passwordEncoder(passwordEncoder());
	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		http 
		           .csrf()
		           .disable()
		           .cors()
		           .disable()
		           .authorizeRequests()
		           .antMatchers("/generate-token","/user/").permitAll()
		           .antMatchers(HttpMethod.OPTIONS).permitAll()
		           .anyRequest().authenticated()
		           .and()
		           .exceptionHandling().authenticationEntryPoint(unauthoarizehandler)
		           .and()
		           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		
	}
	
	
	
}
