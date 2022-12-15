package com.release.iktatoapi.service;


import com.release.iktatoapi.data.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {    
    User findUserByEmail(String email);
}
