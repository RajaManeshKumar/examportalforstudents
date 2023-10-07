


package com.examportal.Service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.Service.UserService;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.repo.RoleRep;
import com.examportal.repo.UserRepositry;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	private RoleRep roleRepo;

	@Override
	public User ceateUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local=this.userRepositry.findByUsername(user.getUsername());
	//	System.out.print(local.getUsername());
		
		if(local!=null) {
			                                     
			System.out.println("User Already exists");
			throw new Exception("User Already exists");
		}
		
		
		else {
			
			for(UserRole ur:userRoles) {
				System.out.println("User Repossitry Saving...."+ur.getRole());
				
				roleRepo.save(ur.getRole());
				
				 
			}
			user.getUserRole().addAll(userRoles);
			 
			local=this.userRepositry.save(user);
			
			
		}
		// TODO Auto-generated method stub
		return local;
	}                   

}
