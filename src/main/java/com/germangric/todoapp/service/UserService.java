package com.germangric.todoapp.service;

import com.germangric.todoapp.dto.UserRegistrationDto;
import com.germangric.todoapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    List<User> getAll();
}