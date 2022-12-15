package com.release.iktatoapi.web.security;

import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.data.repository.UserRepository;
import com.release.iktatoapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findUserByEmail(username);
        return new CustomUserDetails(user);
    }
}
