package com.examportal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.Service.UserService;
import com.examportal.Service.impl.UserServiceImp;
import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserServiceImp userService;

	@PostMapping("/")
	public User creteUser(@RequestBody User user) throws Exception {

		Set<UserRole> userRoles = new HashSet<>();
		System.out.print("I am reachiing here");

		Role role = new Role();
		role.setId(41L);
		role.setRoleName("Admin");

		UserRole userRol = new UserRole();
		userRol.setRole(role);
		userRol.setUser(user);
		userRoles.add(userRol);

		return this.userService.ceateUser(user, userRoles);

	}

}
