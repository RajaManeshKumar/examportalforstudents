package com.examportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.model.Role;

@Repository
public interface RoleRep extends JpaRepository<Role, Long> {

}
