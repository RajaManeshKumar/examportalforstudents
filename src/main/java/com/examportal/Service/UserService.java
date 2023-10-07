package com.examportal.Service;

import java.util.Set;


import org.springframework.stereotype.Service;

import com.examportal.model.User;
import com.examportal.model.UserRole;

public interface UserService {

    // Creatinf use
    public User ceateUser(User user, Set<UserRole> userRoles) throws Exception;

}
