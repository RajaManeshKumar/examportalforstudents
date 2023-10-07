package com.examportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.model.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Long> {
	
	
	public User findByUsername(String userName);

}
