package com.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.examportal.Service.impl.UserServiceImp;
import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;

@SpringBootApplication
public class ExamPortalApplication  {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExamPortalApplication.class, args);
	
		// TODO Auto-generated method stub
	}

}
