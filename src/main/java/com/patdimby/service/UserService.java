package com.patdimby.service;

import com.patdimby.dto.UserRegistrationDto;
import com.patdimby.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
