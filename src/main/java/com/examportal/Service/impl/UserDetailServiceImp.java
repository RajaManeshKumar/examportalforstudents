package com.examportal.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examportal.model.User;
import com.examportal.repo.UserRepositry;


@Service
public class UserDetailServiceImp implements UserDetailsService {

	@Autowired
	private UserRepositry userRepositry;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=this.userRepositry.findByUsername(username);

			if(user==null) {
				
				throw new UsernameNotFoundException("User not Found");
			}

			System.out.println("User Name..."+username);
			System.out.println("User Name1..."+user.getUsername());

		
		return user;
	}

}
